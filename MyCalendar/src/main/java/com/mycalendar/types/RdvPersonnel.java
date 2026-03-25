package com.mycalendar.types;

import com.mycalendar.Event;

import java.time.LocalDateTime;

/**
 * Représente le type d'évènement 'RDV Personnel'
 */
public class RdvPersonnel extends Event {

    /**
     * Constructeur publique
     *
     */
    public RdvPersonnel(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(TypeCode.RDV_PERSONNEL, title, proprietaire, dateDebut, dureeMinutes);
    }



    @Override
    public String getDescription() {
        return "RDV : " + this.title + " à " + this.dateDebut.toString();
    }

}