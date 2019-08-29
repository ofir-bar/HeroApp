package com.ofir.heroapp.model

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import com.ofir.heroapp.Constants.HEROES_DATABASE_NAME
import java.nio.file.Files.exists



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
                    HeroesDatabase::class.java, HEROES_DATABASE_NAME
                    )
                    .allowMainThreadQueries()
                    .build()
                }
            }
            return INSTANCE
        }

        fun getDaoInstance(context: Context): HeroDao{
            return getDatabase(context)!!.heroDao()
        }

        // Room is SQLite behind the scenes. SQLite is a file, so we can check if the file exists
        // It will allow us to know if the DB exist.
        fun isDatabaseExist(context: Context): Boolean {
            val dbFile = context.getDatabasePath(HEROES_DATABASE_NAME)
            return dbFile.exists()
        }

    }


}