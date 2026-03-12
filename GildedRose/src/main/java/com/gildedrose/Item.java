package com.gildedrose;

/**
 * Classe qui représente un item
 */
public class Item {

    /**
     * Nom de l'item
     */
    public String name;

    /**
     * Prix de vente de l'item
     */
    public int sellIn;

    /**
     * Qualité de l'item
     */
    public int quality;

    /**
     * Constructeur publique
     * @param n Nom
     * @param sI Prix de vente
     * @param q Qualité
     */
    public Item(String n, int sI, int q) {
        this.name = n;
        this.sellIn = sI;
        this.quality = q;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
