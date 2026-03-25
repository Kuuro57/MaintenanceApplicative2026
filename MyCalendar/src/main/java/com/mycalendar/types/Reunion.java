package com.mycalendar.types;

import com.mycalendar.Event;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Lieu;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;

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
    public Reunion(Titre title, Personne proprietaire, LocalDateTime dateDebut, Duree dureeMinutes, Lieu lieu, List<Personne> participants) {
        super(TypeCode.REUNION, title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
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