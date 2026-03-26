package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.Dinosaure;

public interface DinosaureDao {

    Dinosaure trouver( Long id ) throws DAOException;

    List<Dinosaure> lister( String champTri, boolean ascendant ) throws DAOException;
    void creer( Dinosaure dinosaure ) throws DAOException;
    void supprimer(Long id) throws DAOException;
    void modifier(Dinosaure dino) throws DAOException; 

    List<Dinosaure> rechercher( String motCle ) throws DAOException;

}
