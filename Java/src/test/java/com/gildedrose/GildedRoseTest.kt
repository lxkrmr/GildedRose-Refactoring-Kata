package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    @Test
    fun foo() {
        // given
        val items = arrayOf(Item("foo", 0, 0))
        val app = GildedRose(items)

        // when
        app.updateQuality()

        // then
        assertEquals("foo", app.items[0].name)
    }
}
