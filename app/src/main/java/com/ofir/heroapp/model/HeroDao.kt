package com.ofir.heroapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): List<Hero>

    @Insert
    fun insert(hero: Hero)
}