package com.mycalendar.types;

import com.mycalendar.Event;
import com.mycalendar.datas.DateEvenement;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;

import java.time.LocalDateTime;

/**
 * Représente le type d'évènement 'RDV Personnel'
 */
public class RdvPersonnel extends Event {

    /**
     * Constructeur publique
     *
     */
    public RdvPersonnel(Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes) {
        super(TypeCode.RDV_PERSONNEL, title, proprietaire, dateDebut, dureeMinutes);
    }



    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime eventStart = this.dateDebut.getDate();
        return !eventStart.isBefore(debut) && !eventStart.isAfter(fin);
    }



    @Override
    public String getDescription() {
        return "RDV : " + this.title.getTitre() + " à " + this.dateDebut.getDate().toString();
    }

}