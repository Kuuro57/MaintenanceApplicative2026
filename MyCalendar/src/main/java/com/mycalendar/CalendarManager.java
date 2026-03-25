package com.mycalendar;

import com.mycalendar.types.Periodique;
import com.mycalendar.types.TypeCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un gestionnaire d'évènements
 */
public class CalendarManager {

    /**
     * Liste contenant tous les évènements ajoutés au calendrier
     */
    public List<Event> events;

    /**
     * Constructeur publique
     */
    public CalendarManager() {
        this.events = new ArrayList<>();
    }



    /**
     * Ajoute un évènement au calendrier
     * @param event Evènement à ajouter
     */
    public void ajouterEvent(Event event) {
        events.add(event);
    }

    /**
     * Retourne tous les évènement compris dans la période donnée en paramètre
     * @param debut Date de début de la recherche
     * @param fin Date de fin de la recherche
     * @return Liste contenant les évènements trouvés dans la période donnée
     */
    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.type.equals(TypeCode.PERIODIQUE)) {
                LocalDateTime temp = e.dateDebut;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(((Periodique) e).getFrequenceJours());
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * Indique si deux évènements sont en conflit (se déroulent en même temps)
     * @param e1 Premier évènement
     * @param e2 Deuxième évènement
     * @return True si les évènements rentrent en conflit, False sinon
     */
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes);
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes);

        if (e1.type.equals("PERIODIQUE") || e2.type.equals("PERIODIQUE")) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut)) {
            return true;
        }
        return false;
    }

    /**
     * Affiche les informations de tous les évènements du calendrier
     */
    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.getDescription());
        }
    }
}