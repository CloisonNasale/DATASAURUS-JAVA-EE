package com.sdzee.beans;

public class Dinosaure {

    private Long   id;
    private String nom;
    private String espece;
    private String ere;
    private String regimeAlimentaire;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece( String espece ) {
        this.espece = espece;
    }

    public String getEre() {
        return ere;
    }

    public void setEre( String ere ) {
        this.ere = ere;
    }

    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void setRegimeAlimentaire( String regimeAlimentaire ) {
        this.regimeAlimentaire = regimeAlimentaire;
    }
}
