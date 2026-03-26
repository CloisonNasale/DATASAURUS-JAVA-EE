package com.sdzee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.beans.Dinosaure;

public class DinosaureDaoImpl implements DinosaureDao {

    private static final String SQL_SELECT_PAR_ID = "SELECT id, nom, espece, ere, regime_alimentaire FROM Dinosaure WHERE id = ?";
    private static final String SQL_SELECT_RECHERCHE = "SELECT id, nom, espece, ere, regime_alimentaire FROM Dinosaure WHERE nom LIKE ? OR espece LIKE ? OR ere LIKE ? OR regime_alimentaire LIKE ?";
    private static final String SQL_INSERT = "INSERT INTO Dinosaure (nom, espece, ere, regime_alimentaire) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM Dinosaure WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE Dinosaure SET nom = ?, espece = ?, ere = ?, regime_alimentaire = ? WHERE id = ?";
    private DAOFactory          daoFactory;

    DinosaureDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Dinosaure trouver( Long id ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dinosaure dinosaure = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = UtilisateurDaoImpl.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                dinosaure = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return dinosaure;
    }

    @Override
    public List<Dinosaure> lister( String champTri, boolean ascendant ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dinosaure> dinosaures = new ArrayList<>();

        String tri = "id";
        if ( "nom".equals( champTri ) || "espece".equals( champTri ) || "ere".equals( champTri ) || "regime_alimentaire".equals( champTri ) ) {
            tri = champTri;
        }

        String ordre = ascendant ? "ASC" : "DESC";
        String sql = "SELECT id, nom, espece, ere, regime_alimentaire FROM Dinosaure ORDER BY " + tri + " " + ordre;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( sql );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                dinosaures.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return dinosaures;
    }

    @Override
    public List<Dinosaure> rechercher( String motCle ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dinosaure> dinosaures = new ArrayList<>();
        String filtre = "%" + motCle + "%";

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = UtilisateurDaoImpl.initialisationRequetePreparee( connexion, SQL_SELECT_RECHERCHE, false, filtre, filtre, filtre, filtre );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
                dinosaures.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return dinosaures;
    }

    private static Dinosaure map( ResultSet resultSet ) throws SQLException {
        Dinosaure dinosaure = new Dinosaure();
        dinosaure.setId( resultSet.getLong( "id" ) );
        dinosaure.setNom( resultSet.getString( "nom" ) );
        dinosaure.setEspece( resultSet.getString( "espece" ) );
        dinosaure.setEre( resultSet.getString( "ere" ) );
        dinosaure.setRegimeAlimentaire( resultSet.getString( "regime_alimentaire" ) );
        return dinosaure;
    }
    
    @Override
    public void creer(Dinosaure dino) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = UtilisateurDaoImpl.initialisationRequetePreparee(connexion, SQL_INSERT, true, 
                                    dino.getNom(), dino.getEspece(), dino.getEre(), dino.getRegimeAlimentaire());
            
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création du dinosaure.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    @Override
    public void supprimer(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = UtilisateurDaoImpl.initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, false, id);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la suppression du dinosaure, aucune ligne supprimée.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
    
    @Override
    public void modifier(Dinosaure dino) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = UtilisateurDaoImpl.initialisationRequetePreparee(connexion, SQL_UPDATE, false, 
                                dino.getNom(), dino.getEspece(), dino.getEre(), dino.getRegimeAlimentaire(), dino.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            UtilisateurDaoImpl.fermeturesSilencieuses(preparedStatement, connexion);
        }
    }
}
