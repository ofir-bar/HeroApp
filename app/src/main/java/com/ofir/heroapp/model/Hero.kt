package com.ofir.heroapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val abilities: ArrayList<String>,
    val image: String = "CHANGE ME TO DEFAULT IMAGE",
    val isFavorite: Boolean = false)
