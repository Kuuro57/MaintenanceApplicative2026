package com.mycalendar.datas;

/**
 * Une personne
 */
public class Personne {

    /**
     * Nom de la personne
     */
    private String nom;



    /**
     * Constructeur publique
     * @param nom Nom de la personne
     */
    public Personne(String nom) {
        this.nom = nom;
    }



    /**
     * Récupère le nom
     * @return String
     */
    public String getNom() { return this.nom; }

}
