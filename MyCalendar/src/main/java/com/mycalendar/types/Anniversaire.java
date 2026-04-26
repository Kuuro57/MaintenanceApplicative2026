package com.mycalendar.types;

import com.mycalendar.datas.*;
import com.mycalendar.types.categories.EventPeriodique;

import java.time.LocalDateTime;

public class Anniversaire extends EventPeriodique {


    /**
     * Constructeur publique
     * @param title Titre de l'évènement
     * @param proprietaire Personne qui fête son anniversaire
     * @param dateDebut Date d'anniversaire
     */
    public Anniversaire(Titre title, Personne proprietaire, DateEvenement dateDebut) {
        // 1440 minutes (24h)
        super(TypeCode.ANNIVERSAIRE, title, proprietaire, dateDebut, new Duree(1440), new Frequence(365));
    }



    @Override
    public String getDescription() {
        return "Anniversaire de " + proprietaire.getNom() + " !";
    }



    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime dateInitiale = this.dateDebut.getDate();

        LocalDateTime occurrenceCetteAnnee = dateInitiale.withYear(debut.getYear());

        return (!occurrenceCetteAnnee.isBefore(debut) && occurrenceCetteAnnee.isBefore(fin))
                || (!occurrenceCetteAnnee.plusYears(1).isBefore(debut) && occurrenceCetteAnnee.plusYears(1).isBefore(fin));
    }
}