package com.ofir.heroapp.model

/*
I choose to use Set from the Collections for the hero "abilities", since attributes should not be duplicated.
 */

data class Hero(val title: String, val abilities: Set<String>, val image: String = "CHANGE ME TO DEFAULT IMAGE", val isFavorite: Boolean = false)
