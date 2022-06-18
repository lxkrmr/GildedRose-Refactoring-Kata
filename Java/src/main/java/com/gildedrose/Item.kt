package com.gildedrose

open class Item(
    val name: String,
    var sellIn: Int,
    var quality: Int
) {

    override fun toString() = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int, quality: Int) : Item(name, sellIn, quality) {
    fun update() {
        age()
        degrade()
    }

    protected open fun age() {
        sellIn -= 1
    }

    protected open fun degrade() {
        if (quality > 0) {
            quality -= 1
        }
        if (sellIn < 0 && quality > 0) {
            quality -= 1
        }
    }
}

class Brie(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality < 50) {
            quality += 1
        }
        if (sellIn < 0 && quality < 50) {
            quality += 1
        }
    }
}

class Pass(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality < 50) {
            quality += 1
            if (sellIn < 10) {
                if (quality < 50) {
                    quality += 1
                }
            }
            if (sellIn < 5) {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
        if (sellIn < 0) {
            quality = 0
        }
    }
}

class Sulfuras(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun age() {
        // sulfuras is not allowed to update anything here
    }

    override fun degrade() {
        // sulfuras is not allowed to update anything here
    }
}

