package com.mycalendar.datas;

/**
 * Titre d'un évènement
 */
public class Titre {

    /**
     * Titre d'un évènement
     */
    private String titre;



    /**
     * Constructeur publique
     * @param titre Titre de l'évènement
     */
    public Titre(String titre) {
        this.titre = titre;
    }



    /**
     * Récupère le titre
     * @return String
     */
    public String getTitre() { return this.titre; }

}
