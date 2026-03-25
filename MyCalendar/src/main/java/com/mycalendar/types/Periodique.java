package com.mycalendar.types;

import com.mycalendar.Event;

import java.time.LocalDateTime;

/**
 * Représente le type d'évènement 'Périodique'
 */
public class Periodique extends Event {

    /**
     * Fréquence de répétition de l'évènement (en jours)
     */
    private final int frequenceJours;



    /**
     * Constructeur publique
     * @param frequenceJours Fréquence de répétition (en jours)
     */
    public Periodique(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(TypeCode.PERIODIQUE,  title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }



    @Override
    public String getDescription() {
        return "Événement périodique : " + this.title + " tous les " + this.frequenceJours + " jours";
    }

    /**
     * Récupère la fréquence de répétition de l'évènement
     * @return int
     */
    public int getFrequenceJours() { return frequenceJours; }

}