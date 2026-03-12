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


    private final static String AGED_BRIE = "Aged Brie";
    private final static String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private final static String SULFURAS = "Sulfuras, Hand of Ragnaros";



    /**
     * Constructeur publique
     * @param items Liste d'items à ajouter au marché
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }



    /**
     * Met à jour la qualité de chaque item sur le marché
     */
    public void updateQualityOfAllItems() {
        for (Item item : items) { item.updateQuality(); }
    }


}
