package com.mycalendar.types.categories;

import com.mycalendar.Event;
import com.mycalendar.datas.*;
import com.mycalendar.types.TypeCode;

import java.time.LocalDateTime;

/**
 * Représente les évènements non périodique
 */
public class EventPeriodique extends Event {

    /**
     * Fréquence de répétition de l'évènement (en jours)
     */
    private final Frequence frequenceJours;



    /**
     * Constructeur privée aux classes enfants
     * @param type         Type de l'évènement
     * @param title        Titre de l'évènement
     * @param proprietaire Propriétaire de l'évènement
     * @param dateDebut    Date de début
     * @param dureeMinutes Durée (en minute)
     */
    protected EventPeriodique(TypeCode type, Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes, Frequence frequenceJours) {
        super(type, title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }



    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime temp = this.dateDebut.getDate();

        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(this.getFrequenceJours());
        }
        return false;
    }



    @Override
    public boolean occupeCreneau(LocalDateTime debutParam, LocalDateTime finParam) {
        LocalDateTime occurrence = this.dateDebut.getDate();
        int duree = this.dureeMinutes.getDuree();

        while (occurrence.isBefore(finParam)) {
            LocalDateTime finOccurrence = occurrence.plusMinutes(duree);

            if (finOccurrence.isAfter(debutParam)) {
                return true;
            }
            occurrence = occurrence.plusDays(this.getFrequenceJours());
        }
        return false;
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
