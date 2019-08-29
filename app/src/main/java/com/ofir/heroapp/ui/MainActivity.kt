package com.ofir.heroapp.ui

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofir.heroapp.R
import com.ofir.heroapp.adapters.ListOfHeroesAdapter
import com.ofir.heroapp.model.Hero
import com.ofir.heroapp.model.HeroesDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var heroesDatabase: HeroesDatabase? = null
    private var listOfHeroesFromRoom: ArrayList<Hero> = ArrayList()

    // TODO: Use this list for testing only
    private val listOfHeroesFromMemory: ArrayList<Hero> = arrayListOf(
        Hero("Wolverin1", arrayListOf("Strong", "Fast"), "someImage"),
        Hero("Spiderman1", arrayListOf("Fast", "Smart"), "someImage"),
        Hero("Superman1", arrayListOf("Can Fly", "Laser-eyes"), "someImage")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroesDatabase = HeroesDatabase.getDatabase(this)!!

        // Insert heroes from local to make sure Room is working properly.
        // In-memory objects will be persisted on the device.
        for(hero in listOfHeroesFromMemory){
            InsertHero(this,hero).execute()
        }
        listOfHeroesFromRoom = GetDataFromDb(this).execute().get() as ArrayList<Hero>


        main_activity_list_of_heroes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListOfHeroesAdapter(listOfHeroesFromRoom, context)
        }

    }

    private class InsertHero(var context: MainActivity, var hero: Hero) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.heroesDatabase!!.heroDao().insert(hero)
            return true
        }
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Log.e("TEST", "ADDED TO DB")
            }
        }
    }
    private class GetDataFromDb(var context: MainActivity) : AsyncTask<Void, Void, List<Hero>>() {
        override fun doInBackground(vararg params: Void?): List<Hero> {
            return context.heroesDatabase!!.heroDao().getAllHeroes()
        }
        override fun onPostExecute(heroesList: List<Hero>?) {
            if (heroesList!!.isNotEmpty()) {
                for (i in 0 until heroesList.size) {
                    Log.e("TEST2", heroesList[i].title)
                }
            }
        }
    }



}