package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("Test de la méthode toString() de la classe Item")
    void testToStringItem() {

        var item = new Item("ANYTHING", 50, 10);

        var res = item.toString();

        assertEquals("ANYTHING, 50, 10", res);

    }

    @ParameterizedTest(name = "{index} - nom: {0}, sellIn: {1}, quality: {2}")
    @CsvSource({
            "'ANYTHING', 1, 1, 0, 0",
            "'Aged Brie', 1, 1, 0, 2",
            "'Backstage passes to a TAFKAL80ETC concert', 1, 1, 0, 4",
            "'ANYTHING', 1, 0, 0, 0",
            "'Sulfuras, Hand of Ragnaros', 1, 1, 1, 1",
            "'Aged Brie', 1, 51, 0, 51",
            "'Backstage passes to a TAFKAL80ETC concert', 12, 1, 11, 2",
            "'Backstage passes to a TAFKAL80ETC concert', 10, 49, 9, 50",
            "'Backstage passes to a TAFKAL80ETC concert', 1, 49, 0, 50",
            "'Aged Brie', -1, 1, -2, 3",
            "'Aged Brie', -1, 51, -2, 51",
            "'Backstage passes to a TAFKAL80ETC concert', -1, 51, -2, 0",
            "'Sulfuras, Hand of Ragnaros', -1, 51, -1, 51",
            "'Sulfuras, Hand of Ragnaros', -1, -1, -1, -1",
            "'ANYTHING', -1, 10, -2, 8"
    })
    void allTest(String name, int sellIn, int quality, int sellInExcepted, int qualityExcepted) {

        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(qualityExcepted, app.items[0].getQuality(), "La qualité n'est pas celle attendue");
        assertEquals(sellInExcepted, app.items[0].getSellIn(), "La valeur de vente n'est pas celle attendue");

    }

}
