package com.example.becamobile2020.presentation.heroes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.becamobile2020.R
import com.example.becamobile2020.data.response.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroesAdapter(
    private val heroesList: List<Character>,
    val OnItemClickListener: ((character: Character) -> Unit)
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): HeroesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroesViewHolder(itemView, OnItemClickListener)
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {

        holder.bind(heroesList[position])

//        holder.itemView.textHeroName.text = heroesList[position].name
//
//        var thumbnail = "${heroesList[position].thumbnail.path}/standard_medium.${heroesList[position].thumbnail.extension}"
//            .split(":")
//
//        Picasso.get().load("https:"+thumbnail[1]).into(holder.itemView.imageHero)


    }

    class HeroesViewHolder(
        itemView: View,
        private val OnItemClickListener: ((character: Character) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val heroName = itemView.textHeroName
        private val heroImg: ImageView = itemView.imageHero

        fun bind(char: Character) {
            heroName.text = char.name
            var thumbnail = "${char.thumbnail.path}/standard_small.${char.thumbnail.extension}"
                .split(":")
            Picasso.get().load("https:" + thumbnail[1]).into(itemView.imageHero)

            itemView.setOnClickListener {
                OnItemClickListener.invoke(char)
            }

        }

    }
}