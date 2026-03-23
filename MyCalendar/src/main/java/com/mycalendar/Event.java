package com.mycalendar;

import java.time.LocalDateTime;

/**
 * Représente un évènement
 */
public class Event {

    /**
     * Type d'évènement "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
     */
    public String type;

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
     * Lieu de l'évènement (utilisé seulement pour REUNION)
     */
    public String lieu;

    /**
     * Participants de l'évènement (séparés par virgules (pour REUNION uniquement))
     */
    public String participants;

    /**
     * Fréquence périodique de l'évènement (uniquement pour PERIODIQUE)
     */
    public int frequenceJours;



    /**
     * Constructeur publique
     * @param type Type de l'évènement
     * @param title Titre de l'évènement
     * @param proprietaire Propriétaire de l'évènement
     * @param dateDebut Date de début
     * @param dureeMinutes Durée (en minute)
     * @param lieu Lieu de l'évènement
     * @param participants Participants de l'évènement
     * @param frequenceJours Fréquence périodique de l'évènement
     */
    public Event(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                 String lieu, String participants, int frequenceJours) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.lieu = lieu;
        this.participants = participants;
        this.frequenceJours = frequenceJours;
    }



    /**
     * Retourne la description de l'évènement
     * @return La description
     */
    public String description() {
        String desc = "";
        if (type.equals("RDV_PERSONNEL")) {
            desc = "RDV : " + title + " à " + dateDebut.toString();
        } else if (type.equals("REUNION")) {
            desc = "Réunion : " + title + " à " + lieu + " avec " + participants;
        } else if (type.equals("PERIODIQUE")) {
            desc = "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
        }
        return desc;
    }
    
}