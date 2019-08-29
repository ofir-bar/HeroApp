package com.ofir.heroapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ofir.heroapp.R
import com.ofir.heroapp.model.Converters
import com.ofir.heroapp.model.Hero
import kotlinx.android.synthetic.main.hero_card.view.*

class ListOfHeroesAdapter(private val listOfHeroes: List<Hero>, private val context: Context): RecyclerView.Adapter<ListOfHeroesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.hero_card, parent, false))
    }

    // Binds each Hero object from the data to a view in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, p1: Int){
        holder.tvHeroTitle.text = listOfHeroes[p1].title
        holder.tvHeroAbilities.text = fromArray(listOfHeroes[p1].abilities)
        holder.ivHeroImage.setImageResource(R.drawable.ic_launcher_background) //TODO: Change it to dynamic image
        holder.ivHeroFavorite.setImageResource(R.drawable.ic_favorite)
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val cardHero: CardView = view.hero_card

        val tvHeroTitle: TextView = view.hero_title
        val tvHeroAbilities: TextView = view.hero_abilities
        val ivHeroImage: ImageView = view.hero_image
        val ivHeroFavorite: ImageView= view.hero_favorite

        init {
            cardHero.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Log.e("TEST", "ONCLICK IS WORKING!")
        }
    }

    private fun fromArray(heroAttributes: ArrayList<String>):String{
        var heroAttributesAsString = ""
        for(heroAttribute in heroAttributes) heroAttributesAsString += ("$heroAttribute,");
        return heroAttributesAsString
    }

}