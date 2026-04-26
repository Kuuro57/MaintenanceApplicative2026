package com.mycalendar;

import com.mycalendar.datas.DateEvenement;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;
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
    public Titre title;

    /**
     * Propriétaire de l'évènement
     */
    public Personne proprietaire;

    /**
     * Date de début
     */
    public DateEvenement dateDebut;

    /**
     * Durée de l'évènement (en minutes)
     */
    public Duree dureeMinutes;



    /**
     * Constructeur privée aux classes enfants
     * @param type Type de l'évènement
     * @param title Titre de l'évènement
     * @param proprietaire Propriétaire de l'évènement
     * @param dateDebut Date de début
     * @param dureeMinutes Durée (en minute)
     */
    protected Event(TypeCode type, Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }



    /**
     * Test si l'évènement a lieu entre le début et la fin passées en paramètres
     * @param debut Date de début
     * @param fin Date de fin
     * @return
     */
    public abstract boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin);



    /**
     * Retourne la description de l'évènement
     * @return La description
     */
    public abstract String getDescription();

}
