package com.mycalendar.datas;

import java.time.LocalDateTime;

/**
 * Date de début d'un évènement
 */
public class DateEvenement {

    /**
     * Date de l'évènement
     */
    private LocalDateTime date;



    /**
     * Constructeur publique
     * @param jour Jour de début de l'évènement
     * @param mois Mois de début de l'évènement
     * @param annee Année de début de l'évènement
     * @param heure Heure de début de l'évènement
     */
    public DateEvenement(int jour, int mois, int annee, HeureDebut heure) {
        this.date = LocalDateTime.of(annee, mois, jour, heure.getHeures(), heure.getMinutes());
    }



    /**
     * Récupère la date de début de l'évènement
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return this.date;
    }

}
