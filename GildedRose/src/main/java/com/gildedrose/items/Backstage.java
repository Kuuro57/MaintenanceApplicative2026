package com.gildedrose.items;

public class Backstage extends Item {

    /**
     * Constructeur publique
     *
     * @param sI Prix de vente
     * @param q  Qualité
     */
    public Backstage(int sI, int q) {
        super(sI, q);
        this.name = "Backstage passes to a TAFKAL80ETC concert";
    }


    @Override
    public void updateInformations() {

        if (sellIn < 0) {
            this.quality = 0;
        }
        else if (this.quality < 50) {
            this.quality++;

            if (this.quality < 50) {
                if (this.sellIn < 6) { this.quality += 2; }
                else if (this.sellIn < 11) { this.quality++; }
            }
        }

        this.sellIn--;

    }

}
