package com.gildedrose.items;

/**
 * Classe abstraite qui représente un item
 */
public abstract class Item {

    /**
     * Nom de l'item
     */
    protected String name;

    /**
     * Prix de vente de l'item
     */
    protected int sellIn;

    /**
     * Qualité de l'item
     */
    protected int quality;



    /**
     * Constructeur publique
     * @param sI Prix de vente
     * @param q Qualité
     */
    public Item(int sI, int q) {
        this.sellIn = sI;
        this.quality = q;
    }



    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }


    /**
     * Méthode qui met à jour la qualité de l'item
     */
    public abstract void updateQuality();


    // Getters
    public int getQuality() { return quality; }
    public int getSellIn() { return sellIn; }

}
