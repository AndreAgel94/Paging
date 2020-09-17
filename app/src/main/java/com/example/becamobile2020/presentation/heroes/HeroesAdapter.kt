package com.example.becamobile2020.presentation.heroes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.becamobile2020.R
import com.example.becamobile2020.data.response.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroesAdapter(
   // private val heroesList: List<Character>,
    private val OnItemClickListener: ((character: Character) -> Unit)
) : PagedListAdapter<Character ,HeroesAdapter.HeroesViewHolder>(characterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): HeroesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroesViewHolder(itemView, OnItemClickListener)
    }

//    override fun getItemCount(): Int {
//        return heroesList.size
//    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }
    }

    class HeroesViewHolder(
        itemView: View,
        private val OnItemClickListener: ((character: Character) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val heroName = itemView.textHeroName

        fun bind(char: Character) {
            heroName.text = char.name

            val url = "${char.thumbnail.path}/standard_medium.${char.thumbnail.extension}"
                .split(":")
            Picasso.get().load("https:" + url[1]).into(itemView.imageHero)


            itemView.setOnClickListener {
                OnItemClickListener.invoke(char)
            }

        }

    }
    companion object{
        val characterDiff = object : DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

        }
    }
}