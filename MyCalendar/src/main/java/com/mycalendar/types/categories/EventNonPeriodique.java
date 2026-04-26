package com.mycalendar.types.categories;

import com.mycalendar.Event;
import com.mycalendar.datas.DateEvenement;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;
import com.mycalendar.types.TypeCode;

import java.time.LocalDateTime;

/**
 * Représente les évènements non périodique
 */
public class EventNonPeriodique extends Event {


    /**
     * Constructeur privée aux classes enfants
     * @param type         Type de l'évènement
     * @param title        Titre de l'évènement
     * @param proprietaire Propriétaire de l'évènement
     * @param dateDebut    Date de début
     * @param dureeMinutes Durée (en minute)
     */
    protected EventNonPeriodique(TypeCode type, Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes) {
        super(type, title, proprietaire, dateDebut, dureeMinutes);
    }



    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime eventStart = this.dateDebut.getDate();
        return !eventStart.isBefore(debut) && !eventStart.isAfter(fin);
    }



    @Override
    public boolean occupeCreneau(LocalDateTime debutParam, LocalDateTime finParam) {
        LocalDateTime monDebut = this.dateDebut.getDate();
        LocalDateTime maFin = monDebut.plusMinutes(this.dureeMinutes.getDuree());

        return monDebut.isBefore(finParam) && maFin.isAfter(debutParam);
    }



    @Override
    public String getDescription() {
        return "Evenement non périodique";
    }

}
