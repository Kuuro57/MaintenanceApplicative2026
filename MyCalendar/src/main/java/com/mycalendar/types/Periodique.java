package com.mycalendar.types;

import com.mycalendar.Event;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Frequence;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;

import java.time.LocalDateTime;

/**
 * Représente le type d'évènement 'Périodique'
 */
public class Periodique extends Event {

    /**
     * Fréquence de répétition de l'évènement (en jours)
     */
    private final Frequence frequenceJours;



    /**
     * Constructeur publique
     * @param frequenceJours Fréquence de répétition (en jours)
     */
    public Periodique(Titre title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes, Frequence frequenceJours) {
        super(TypeCode.PERIODIQUE,  title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }



    @Override
    public String getDescription() {
        return "Événement périodique : " + this.title.getTitre() + " tous les " + this.frequenceJours.getFrequence() + " jours";
    }

    /**
     * Récupère la fréquence de répétition de l'évènement
     * @return int
     */
    public int getFrequenceJours() { return frequenceJours.getFrequence(); }

}