package com.gildedrose;

import com.gildedrose.items.Item;

/**
 * Classe qui représente la GildedRose (référence à World Of Warcraft)
 */
class GildedRose {

    /**
     * Liste d'items
     */
    Item[] items;



    /**
     * Constructeur publique
     * @param items Liste d'items à ajouter au marché
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }



    /**
     * Met à jour les données de chaque item sur le marché
     */
    public void updateInformationsOfAllItems() {
        for (Item item : items) { item.updateInformations(); }
    }


}
