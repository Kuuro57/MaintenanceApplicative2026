package com.gildedrose.items;

public class AgedBrie extends Item implements Updatable {

    /**
     * Constructeur publique
     *
     * @param sI Prix de vente
     * @param q  Qualité
     */
    public AgedBrie(int sI, int q) {
        super(sI, q);
        this.name = "Aged Brie";
    }


    @Override
    public void updateInformations() {

        if (this.quality < 50) {
            this.quality++;
            if (this.sellIn < 0) { this.quality++; }
        }

        this.sellIn--;

    }

}
