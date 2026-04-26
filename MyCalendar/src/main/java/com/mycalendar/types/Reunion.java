package com.mycalendar.types;

import com.mycalendar.Event;
import com.mycalendar.datas.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Représente le type d'évènement 'Réunion'
 */
public class Reunion extends Event {

    /**
     * Lieu où se déroule l'évènement
     */
    private Lieu lieu;

    /**
     * Participants de l'évènement
     */
    private List<Personne> participants;



    /**
     * Constructeur publique
     *
     */
    public Reunion(Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes, Lieu lieu, List<Personne> participants) {
        super(TypeCode.REUNION, title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
    }



    @Override
    public boolean estDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime eventStart = this.dateDebut.getDate();
        return !eventStart.isBefore(debut) && !eventStart.isAfter(fin);
    }



    @Override
    public String getDescription() {
        var listOfNames = this.participants.stream()
                .map(Personne::getNom)
                .reduce("", (acc, p) -> acc + ", " + p)
                .replaceFirst(", ", "");

        return "Réunion : " + this.title.getTitre() + " à " + this.lieu.getLieu() + " avec " + listOfNames;
    }
}