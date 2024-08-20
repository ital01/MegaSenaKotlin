package com.example.megasenakotlin

import kotlin.random.Random

data class Numbers(
    val n1: Int = 0,
    val n2: Int = 0,
    val n3: Int = 0,
    val n4: Int = 0,
    val n5: Int = 0,
    val n6: Int = 0
) {
    companion object {
        fun generateNumbers(): List<Int> {
            return List(6) { Random.nextInt(1, 61) }
        }
    }
}
