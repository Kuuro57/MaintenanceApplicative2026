package com.mycalendar.types;

import com.mycalendar.Event;

import java.time.LocalDateTime;

/**
 * Représente le type d'évènement 'Réunion'
 */
public class Reunion extends Event {

    /**
     * Lieu où se déroule l'évènement
     */
    private String lieu;

    /**
     * Participants de l'évènement
     */
    private String participants;



    /**
     * Constructeur publique
     *
     */
    public Reunion(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        super(TypeCode.REUNION, title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
    }



    @Override
    public String getDescription() {
        return "Réunion : " + this.title + " à " + this.lieu + " avec " + this.participants;
    }
}