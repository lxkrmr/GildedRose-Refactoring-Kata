package com.gildedrose

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

operator fun ((Int, Int) -> Int).times(multiplier: Int) = { p1: Int, p2: Int ->
    this(p1, p2) * multiplier
}
