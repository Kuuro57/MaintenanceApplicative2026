package com.mycalendar.types;

import com.mycalendar.datas.DateEvenement;
import com.mycalendar.datas.Duree;
import com.mycalendar.datas.Personne;
import com.mycalendar.datas.Titre;
import com.mycalendar.types.categories.EventNonPeriodique;


/**
 * Représente le type d'évènement 'RDV Personnel'
 */
public class RdvPersonnel extends EventNonPeriodique {

    /**
     * Constructeur publique
     *
     */
    public RdvPersonnel(Titre title, Personne proprietaire, DateEvenement dateDebut, Duree dureeMinutes) {
        super(TypeCode.RDV_PERSONNEL, title, proprietaire, dateDebut, dureeMinutes);
    }



    @Override
    public String getDescription() {
        return "RDV : " + this.title.getTitre() + " à " + this.dateDebut.getDate().toString();
    }

}