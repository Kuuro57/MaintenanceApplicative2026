package com.mycalendar.datas;

public class HeureDebut {

    /**
     * Heures de début de l'évènement
     */
    private int heures;

    /**
     * Minutes de début de l'évènement
     */
    private int minutes;



    /**
     * Constructeur publique
     * @param heures Heures de début de l'évènement
     * @param minutes Minutes de début de l'évènement
     */
    public HeureDebut(int heures, int minutes) {
        this.heures = heures;
        this.minutes = minutes;
    }



    // GETTERS
    public int getHeures() { return heures; }
    public int getMinutes() { return minutes; }
}
