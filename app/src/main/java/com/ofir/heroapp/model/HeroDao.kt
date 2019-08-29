package com.ofir.heroapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): List<Hero>

    //TODO: For testing only.
    // Use this to insert dumb data before fetching data from the web
    @Insert
    fun insert(hero: Hero)

}