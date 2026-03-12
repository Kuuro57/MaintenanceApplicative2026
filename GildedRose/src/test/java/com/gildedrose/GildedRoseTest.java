package com.gildedrose;

import com.gildedrose.items.AgedBrie;
import com.gildedrose.items.Backstage;
import com.gildedrose.items.Item;
import com.gildedrose.items.Sulfuras;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("Test de la méthode toString() de la classe Item")
    void testToStringItem() {

        var item = new AgedBrie(50, 10);

        var res = item.toString();

        assertEquals("Aged Brie, 50, 10", res);

    }

    @ParameterizedTest(name = "{index} - nom: {0}, sellIn: {1}, quality: {2}")
    @CsvSource({
            "'Aged Brie', 1, 1, 0, 2",
            "'Backstage passes to a TAFKAL80ETC concert', 1, 1, 0, 4",
            "'Sulfuras, Hand of Ragnaros', 1, 1, 1, 1",
            "'Aged Brie', 1, 51, 0, 51",
            "'Backstage passes to a TAFKAL80ETC concert', 12, 1, 11, 2",
            "'Backstage passes to a TAFKAL80ETC concert', 10, 49, 9, 50",
            "'Backstage passes to a TAFKAL80ETC concert', 1, 49, 0, 50",
            "'Backstage passes to a TAFKAL80ETC concert', 7, 48, 6, 50",
            "'Backstage passes to a TAFKAL80ETC concert', 1, 51, 0, 51",
            "'Aged Brie', -1, 1, -2, 3",
            "'Aged Brie', -1, 51, -2, 51",
            "'Backstage passes to a TAFKAL80ETC concert', -1, 51, -2, 0",
            "'Sulfuras, Hand of Ragnaros', -1, 51, -1, 51",
            "'Sulfuras, Hand of Ragnaros', -1, -1, -1, -1"
    })
    void allTest(String name, int sellIn, int quality, int sellInExcepted, int qualityExcepted) {

        Item item;
        switch (name) {
            case "Aged Brie" -> item = new AgedBrie(sellIn, quality);
            case "Backstage passes to a TAFKAL80ETC concert" -> item = new Backstage(sellIn, quality);
            case "Sulfuras, Hand of Ragnaros" -> item = new Sulfuras(sellIn, quality);
            default -> item = new AgedBrie(9999999, 9999999);
        }

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);

        app.updateInformationsOfAllItems();

        assertEquals(qualityExcepted, app.items[0].getQuality(), "La qualité n'est pas celle attendue");
        assertEquals(sellInExcepted, app.items[0].getSellIn(), "La valeur de vente n'est pas celle attendue");

    }

}
