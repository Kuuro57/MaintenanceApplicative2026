package com.mycalendar.datas;

/**
 * Fréquence de répétition d'un évènement (en jours)
 */
public class Frequence {

    /**
     * Fréquence de répétition d'un évènement
     */
    private int frequence;



    /**
     * Constructeur publique
     * @param frequence Fréquence de répétition de l'évènement
     */
    public Frequence(int frequence) {
        this.frequence = frequence;
    }



    /**
     * Récupère la Fréquence de répétition
     * @return int
     */
    public int getFrequence() { return this.frequence; }

}
