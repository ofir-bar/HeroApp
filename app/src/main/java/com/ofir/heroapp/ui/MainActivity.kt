package com.ofir.heroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofir.heroapp.R
import com.ofir.heroapp.adapters.ListOfHeroesAdapter
import com.ofir.heroapp.model.Hero
import com.ofir.heroapp.model.HeroesDatabase
import com.ofir.heroapp.model.HeroesDatabase.Companion.getDaoInstance
import com.ofir.heroapp.network.HeroappsApiService
import com.ofir.heroapp.network.Model
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    private var herosList: List<Hero> = ArrayList()
    private var heroesDatabase: HeroesDatabase? = null

    // Only initialize a networking if it is needed.
    // The user might already have a copy of the remote DB locally.
    private val heroappsApiService by lazy {
        HeroappsApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        heroesDatabase = HeroesDatabase.getDatabase(this)!!

        // All send network request to fetch data, if it is not stored locally.
        if (!HeroesDatabase.isDatabaseExist(this)){

            val call = heroappsApiService.getAllHeroesData()
            call.enqueue(object : Callback<List<Model.Hero>>{
                override fun onResponse(call: Call<List<Model.Hero>>?, response: Response<List<Model.Hero>>?) {
                    if (response!!.code() == 200) {
                        val heroResponse = response.body()!!
                        for(hero in heroResponse){
                            Log.e(TAG, hero.toString())
                            getDaoInstance(baseContext).insert(Hero(hero.title, hero.abilities,hero.image, hero.isFavorite))
                        }
                    }
                }

                override fun onFailure(call: Call<List<Model.Hero>>?, t: Throwable?) {
                    Log.e(TAG, "ERROR PARSING OR NETWORKING")}
            })
        }
        else{
            Log.e(TAG, "Database already exists, no need to send a network request")
        }

        herosList = HeroesDatabase.getDaoInstance(this).getAllHeroes()

        main_activity_list_of_heroes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListOfHeroesAdapter(herosList, context)
        }

    }

}