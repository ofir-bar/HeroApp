package com.ofir.heroapp.model

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters

@Database(entities = arrayOf(Hero::class), version = 1)
@TypeConverters(Converters::class)

abstract class HeroesDatabase : RoomDatabase() {
    private val DATABASE_NAME = "heroes_list"
    abstract fun heroDao(): HeroDao

    // Makes sure we are running only one instance of RoomDB at any given time.
    companion object {
        private var INSTANCE: HeroesDatabase? = null

        fun getDatabase(context: Context): HeroesDatabase? {
            if (INSTANCE == null) {
                synchronized(HeroesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        HeroesDatabase::class.java, "chapter.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}