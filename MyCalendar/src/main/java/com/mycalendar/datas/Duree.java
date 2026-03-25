package com.mycalendar.datas;

/**
 * Durée d'un évènement (en minutes)
 */
public class Duree {

    /**
     * Durée d'un évènement
     */
    private int duree;



    /**
     * Constructeur publique
     * @param duree Durée de l'évènement
     */
    public Duree(int duree) {
        this.duree = duree;
    }



    /**
     * Récupère la durée
     * @return int
     */
    public int getDuree() { return this.duree; }

}
