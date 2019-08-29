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
import com.bumptech.glide.Glide
import com.ofir.heroapp.R
import com.ofir.heroapp.model.Hero
import com.ofir.heroapp.ui.FullscreenHeroImageActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.hero_card.view.*
import org.jetbrains.anko.startActivity

class ListOfHeroesAdapter(private val listOfHeroes: List<Hero>, private val context: Context): RecyclerView.Adapter<ListOfHeroesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.hero_card, parent, false))
    }

    // Binds each Hero object from the data to a view in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, p1: Int){
        holder.tvHeroTitle.text = listOfHeroes[p1].title
        holder.tvHeroAbilities.text = fromArray(listOfHeroes[p1].abilities)
        Glide.with(context).load(listOfHeroes[p1].image).into(holder.ivHeroImage)

        //TODO: Add a check if hero is the favorite one.
        holder.ivHeroFavorite.setImageResource(R.drawable.ic_favorite)
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    class MyViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val cardHero: CardView = view.hero_card

        val tvHeroTitle: TextView = view.hero_title
        val tvHeroAbilities: TextView = view.hero_abilities
        val ivHeroImage: CircleImageView = view.hero_image
        val ivHeroFavorite: ImageView= view.hero_favorite

        init {
            cardHero.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val context = itemView.context
            // Pass the Image data to the next Activity
            context.startActivity<FullscreenHeroImageActivity>()
        }
    }

    private fun fromArray(heroAttributes: ArrayList<String>):String{
        var heroAttributesAsString = ""
        for(heroAttribute in heroAttributes) heroAttributesAsString += ("$heroAttribute,");
        return heroAttributesAsString
    }

}