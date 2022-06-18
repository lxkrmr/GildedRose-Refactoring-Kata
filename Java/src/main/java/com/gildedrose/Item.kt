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
        update1()
        update2()
        update3()
    }

    protected open fun update1() {
        if (quality > 0) {
            quality -= 1
        }
    }

    protected open fun update2() {
        sellIn -= 1
    }

    protected open fun update3() {
        if (sellIn < 0 && quality > 0) {
            quality = quality - 1
        }
    }
}

class Brie(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality += 1
        }
    }

    override fun update3() {
        if (sellIn < 0 && quality < 50) {
            quality += 1
        }
    }
}

class Pass(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality += 1
            if (sellIn < 11) {
                if (quality < 50) {
                    quality += 1
                }
            }
            if (sellIn < 6) {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
    }

    override fun update3() {
        if (sellIn < 0) {
            quality = 0
        }
    }
}

class Sulfuras(name: String, sellIn: Int, quality: Int) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        // sulfuras is not allowed to update anything here
    }

    override fun update2() {
        // sulfuras is not allowed to update anything here
    }

    override fun update3() {
        // sulfuras is not allowed to update anything here
    }
}

