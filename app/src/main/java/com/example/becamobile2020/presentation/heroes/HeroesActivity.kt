package com.example.becamobile2020.presentation.heroes


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.becamobile2020.R
import com.example.becamobile2020.presentation.details.HeroDetailsActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_heroes.*

class HeroesActivity : AppCompatActivity() {

    private val viewModel : HeroesViewModel by lazy {
        ViewModelProviders.of(this).get(HeroesViewModel::class.java)
    }

    private val herosAdapter: HeroesAdapter by lazy {
        HeroesAdapter(){
                        val intent = HeroDetailsActivity.getStartIntent(
                            this@HeroesActivity, it.id
                        )
                        this@HeroesActivity.startActivity(intent)}
    }

    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)

        toolbarMain.title = getString(R.string.heroes_title)
        setSupportActionBar(toolbarMain)

        val llm = LinearLayoutManager(this)
        recyclerHeroes.layoutManager=llm
        recyclerHeroes.adapter = herosAdapter
        subscribeToList()


//        val heroesViewModel: HeroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)

//        //Observa o livedata do VM
//        heroesViewModel.heroesLiveData.observe(this, Observer {
//            it?.let {
//                with(recyclerHeroes) {
//                    layoutManager =
//                        LinearLayoutManager(this@HeroesActivity, RecyclerView.VERTICAL, false)
//                        setHasFixedSize(true)
//                    //Seta ao adapter uma lista de character
//
//                    adapter = HeroesAdapter() {
//                        //recebendo no lambda um character
//                        // e passando o character para a getIntent da details.
//                        val intent = HeroDetailsActivity.getStartIntent(
//
//                            this@HeroesActivity, it.id
//                        )
//
//                        this@HeroesActivity.startActivity(intent)
//
//                    }
//                }
//            }
//        })
//
//        heroesViewModel.getHeroes()


    }


    private fun subscribeToList() {
        val disposable = viewModel.characterList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {Log.d("list" , "lista:$it")
                    herosAdapter.submitList(it)
                    if (recyclerState != null) {
                        recyclerHeroes.layoutManager?.onRestoreInstanceState(recyclerState)
                        recyclerState = null
                    }
                },
                { e ->
                    Log.e("NGVL", "Error", e)
                }
            )
    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean{

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val item : MenuItem = menu.findItem(R.id.menu_search)

        searchView.setMenuItem(item)
        val heroesViewModel: HeroesViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                heroesViewModel.getHeroesSelected(query)
                return false

            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })

        return true
    }

}