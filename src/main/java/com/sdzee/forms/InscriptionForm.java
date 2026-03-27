package com.sdzee.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Utilisateur;

public final class InscriptionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_NOM    = "nom";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String nom = getValeurChamp( request, CHAMP_NOM );

        Utilisateur utilisateur = new Utilisateur();

        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( motDePasse );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }

        return utilisateur;
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse == null ) {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
        if ( motDePasse.length() < 8 ) {
            throw new Exception( "Le mot de passe doit contenir au moins 8 caractères." );
        }
        if ( !motDePasse.matches( ".*[A-Z].*" ) ) {
            throw new Exception( "Le mot de passe doit contenir au moins une lettre majuscule." );
        }
        if ( !motDePasse.matches( ".*[0-9].*" ) ) {
            throw new Exception( "Le mot de passe doit contenir au moins un chiffre." );
        }
        if ( !motDePasse.matches( ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*" ) ) {
            throw new Exception( "Le mot de passe doit contenir au moins un caractère spécial (!@#$%^&*...)." );
        }
    }

    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 5 ) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 5 caractères." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
