package com.mycalendar.datas;

public class EventId {

    private static int LAST_ID = 0;

    /**
     * Identifiant unique d'un évènement
     */
    private int id;



    private EventId(int newId) {
        this.id = newId;
    }



    /**
     * Récupère l'id de l'évènement
     * @return int
     */
    public int getId() {
        return id;
    }



    /**
     * Retourne un nouvel objet EventId avec un id unique
     * @return EventId
     */
    public static EventId getNewId() {
        return new EventId(++LAST_ID);
    }

}
