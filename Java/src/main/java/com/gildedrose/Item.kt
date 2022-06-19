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
    val standard: (Int, Int) -> Int = { sellIn, _ ->
        when {
            sellIn < 0 -> 2
            else -> 1
        }
    }
    val brie: (Int, Int) -> Int = { sellIn, _ ->
        when {
            sellIn < 0 -> -2
            else -> -1
        }
    }
    val pass: (Int, Int) -> Int = { sellIn, quality ->
        when {
            sellIn < 0 -> quality
            sellIn < 5 -> -3
            sellIn < 10 -> -2
            else -> -1
        }
    }
    val none: (Int, Int) -> Int = { _, _ -> 0 }
}

object Saturation {
    val standard: (Int) -> Int = { quality ->
        when {
            quality < 0 -> 0
            quality > 50 -> 50
            else -> quality
        }
    }
    val none: (Int) -> Int = { quality -> quality }
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
    degradation = Degradation.brie
)

fun Pass(name: String, sellIn: Int, quality: Int) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = Degradation.pass
)

