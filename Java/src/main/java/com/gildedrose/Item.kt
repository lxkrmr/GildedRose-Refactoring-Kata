package com.gildedrose

open class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {

    override fun toString() = "$name, $sellIn, $quality"
}

object Aging {
    val standard: () -> Int = { 1 }
    val none: () -> Int = { 0 }
}

object Degradation {
    val standard: (Int, Int) -> Int = { currentSellIn, _ ->
        when {
            currentSellIn < 0 -> 2
            else -> 1
        }
    }
    val none: (Int, Int) -> Int = { _, _ -> 0 }
}

object Saturation {
    val standard: (Int) -> Int = { currentQuality ->
        when {
            currentQuality < 0 -> 0
            currentQuality > 50 -> 50
            else -> currentQuality
        }
    }
    val none: (Int) -> Int = { currentQuality -> currentQuality }
}

class BaseItem(
    name: String,
    sellIn: Int,
    quality: Int,
    private val aging: () -> Int = Aging.standard,
    private val degradation: (Int, Int) -> Int = Degradation.standard,
    private val saturation: (Int) -> Int = Saturation.standard
) : Item(name, sellIn, quality) {
    fun update() {
        sellIn = sellIn - aging()
        quality = saturation(quality - degradation(sellIn, quality))
    }

}

fun Sulfuras(name: String, sellIn: Int, quality: Int) = BaseItem(
    name,
    sellIn,
    quality,
    aging = Aging.none,
    degradation = Degradation.none,
    saturation = Saturation.none
)

fun Brie(name: String, sellIn: Int, quality: Int) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { currentSellIn, _ ->
        when {
            currentSellIn < 0 -> -2
            else -> -1
        }
    }
)

fun Pass(name: String, sellIn: Int, quality: Int) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { currentSellIn, currentQuality ->
        when {
            currentSellIn < 0 -> currentQuality
            currentSellIn < 5 -> -3
            currentSellIn < 10 -> -2
            else -> -1
        }
    }
)

fun Conjured(name: String, sellIn: Int, quality: Int) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { curretnSellIn, _ ->
        when {
            curretnSellIn < 0 -> 4
            else -> 2
        }
    }
)


