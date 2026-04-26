package com.mycalendar.types;

import com.mycalendar.datas.*;
import com.mycalendar.types.categories.EventPeriodique;

/**
 * Représente un entraînement sportif
 */
public class Entrainement extends EventPeriodique {

    /**
     * Constructeur publique
     */
    public Entrainement(Titre title, Personne proprietaire, DateEvenement dateDebut,
                        Duree dureeMinutes, Frequence frequenceJours) {
        super(TypeCode.ENTRAINEMENT, title, proprietaire, dateDebut, dureeMinutes, frequenceJours);
    }



    @Override
    public String getDescription() {
        return "Entraînement de " + title.getTitre() +
                " (tous les " + getFrequenceJours() + " jours)";
    }
}