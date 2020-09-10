package com.example.becamobile2020.presentation.heroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.becamobile2020.R
import com.example.becamobile2020.data.model.Hero
import kotlinx.android.synthetic.main.activity_heroes.*

class HeroesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)

        toolbarMain.title = getString(R.string.heroes_title)
        setSupportActionBar(toolbarMain)


        val heroesViewModel : HeroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)

        heroesViewModel.heroesLiveData.observe(this, Observer {
            it?.let {

                for (item in it) {

                   // Log.i("maluco2222", item.heroimage + item.extension)

                }

                with(recyclerHeroes){
                    layoutManager = LinearLayoutManager(this@HeroesActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = HeroesAdapter(it)
                }
            }
        })

        heroesViewModel.getHeroes()
    }


}