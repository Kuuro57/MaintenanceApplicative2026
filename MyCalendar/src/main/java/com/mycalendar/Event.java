package com.mycalendar;

import com.mycalendar.types.TypeCode;

import java.time.LocalDateTime;

public abstract class Event {

    /**
     * Type de l'évènement
     */
    public final TypeCode type;

    /**
     * Titre de l'évènement
     */
    public String title;

    /**
     * Propriétaire de l'évènement
     */
    public String proprietaire;

    /**
     * Date de début
     */
    public LocalDateTime dateDebut;

    /**
     * Durée de l'évènement (en minutes)
     */
    public int dureeMinutes;



    /**
     * Constructeur privée aux classes enfants
     * @param type Type de l'évènement
     * @param title Titre de l'évènement
     * @param proprietaire Propriétaire de l'évènement
     * @param dateDebut Date de début
     * @param dureeMinutes Durée (en minute)
     */
    protected Event(TypeCode type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    /**
     * Retourne la description de l'évènement
     * @return La description
     */
    public abstract String getDescription();

}
