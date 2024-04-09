package com.example.myapplication

import kotlin.random.Random

fun <T> List<T>.randomOrNull(random: Random = Random): T? {
    return if (isNotEmpty()) get(random.nextInt(size)) else null
}