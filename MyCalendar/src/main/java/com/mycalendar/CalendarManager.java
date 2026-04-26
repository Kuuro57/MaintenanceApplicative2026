package com.mycalendar;

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
        return e1.enConflitAvec(e2);
    }



    /**
     * Affiche les informations de tous les évènements du calendrier
     */
    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.getDescription());
        }
    }



    /**
     * Supprime un évènement par rapport à l'id passé en paramètre
     *
     * @param id Id de l'évènement que l'on veut supprimer
     */
    public void supprimerEvenement(int id) {
        this.events.removeIf(event -> event.id.getId() == id);
    }

}