package com.gildedrose

typealias GildeRose = List<Item>
fun GildeRose.updated() = map { it.updated() }

