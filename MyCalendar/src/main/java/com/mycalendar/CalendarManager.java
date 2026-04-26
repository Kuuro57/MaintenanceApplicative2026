package com.mycalendar;

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
        return events.stream()
                .filter(e -> e.estDansPeriode(debut, fin))
                .toList();
    }



    /**
     * Indique si deux évènements sont en conflit (se déroulent en même temps)
     * @param e1 Premier évènement
     * @param e2 Deuxième évènement
     * @return True si les évènements rentrent en conflit, False sinon
     */
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime debut1 = e1.dateDebut.getDate();
        LocalDateTime debut2 = e2.dateDebut.getDate();

        LocalDateTime fin1 = debut1.plusMinutes(e1.dureeMinutes.getDuree());
        LocalDateTime fin2 = debut2.plusMinutes(e2.dureeMinutes.getDuree());

        if (e1.type.equals(TypeCode.PERIODIQUE) || e2.type.equals(TypeCode.PERIODIQUE)) {
            return false; // Simplification abusive
        }

        return debut1.isBefore(fin2) && fin1.isAfter(debut2);
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