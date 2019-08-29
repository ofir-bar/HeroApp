package com.ofir.heroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ofir.heroapp.R
import com.ofir.heroapp.adapters.ListOfHeroesAdapter
import com.ofir.heroapp.model.Hero
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // TODO: Use this list for testing only
    private val setOfHeros: Set<Hero> = setOf(
        Hero("Wolverin", setOf("Strong", "Fast"), "someImage"),
        Hero("Spiderman", setOf("Fast", "Smart"), "someImage"),
        Hero("Superman", setOf("Can Fly", "Laser-eyes"), "someImage")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_list_of_heroes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListOfHeroesAdapter(setOfHeros ,context)
        }

    }

}