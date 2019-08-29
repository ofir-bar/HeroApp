package com.ofir.heroapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ofir.heroapp.R
import com.ofir.heroapp.model.Hero

class ListOfHeroesAdapter(private val listOfHeroes: List<Hero>, private val context: Context): RecyclerView.Adapter<ListOfHeroesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.hero_card, parent, false))
    }

    // Binds each Hero object from the data to a view in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, p1: Int){
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        init {
//            someHero.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

        }
    }


}