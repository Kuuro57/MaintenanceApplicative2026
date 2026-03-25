package com.mycalendar.datas;

/**
 * Lieu d'un évènement
 */
public class Lieu {

    /**
     * Lieu d'un évènement
     */
    private String lieu;



    /**
     * Constructeur publique
     * @param lieu Lieu de l'évènement
     */
    public Lieu(String lieu) {
        this.lieu = lieu;
    }



    /**
     * Récupère le lieu
     * @return String
     */
    public String getLieu() { return this.lieu; }

}
