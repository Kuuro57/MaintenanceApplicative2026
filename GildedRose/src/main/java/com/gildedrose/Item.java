package com.gildedrose;

/**
 * Classe qui représente un item
 */
public class Item {

    /**
     * Nom de l'item
     */
    private String name;

    /**
     * Prix de vente de l'item
     */
    private int sellIn;



    /**
     * Qualité de l'item
     */
    private int quality;

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


    // Getters et Setters

    public int getQuality() { return quality; }
    public void setQuality(int quality) { this.quality = quality; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSellIn() { return sellIn; }
    public void setSellIn(int sellIn) { this.sellIn = sellIn; }
}
