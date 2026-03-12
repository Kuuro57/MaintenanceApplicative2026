package com.gildedrose.items;

public class AgedBrie extends Item {

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
    public void updateQuality() {

        if (this.quality < 50) {
            this.quality += 1;
        }

        this.sellIn -= 1;

        if (this.sellIn < 0 && this.quality < 50) {
            this.quality += 1;
        }

    }

}
