package com.ofir.heroapp.network

object Model {
    data class Hero(val title: String, val abilities: ArrayList<String>, val image: String, val isFavorite: Boolean)
}