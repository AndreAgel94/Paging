package com.example.becamobile2020.presentation.heroes


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.becamobile2020.data.ApiService
import com.example.becamobile2020.data.paging.CharactersDataSourceFactory
import com.example.becamobile2020.data.response.Character
import com.example.becamobile2020.data.response.HeroesResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesViewModel : ViewModel(){

    val _heroesLiveData = MutableLiveData<List<Character>>()
    val heroesLiveData : LiveData<List<Character>> = _heroesLiveData // livedata = MutableLiveData

    var characterList: Observable<PagedList<Character>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 20
    private val sourceFactory : CharactersDataSourceFactory

    init {
        sourceFactory = CharactersDataSourceFactory(compositeDisposable, ApiService.service)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()

        characterList = RxPagedListBuilder(sourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
        Log.i("list","list: $characterList")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    fun getHeroes(){ // aplica o método enqueue a getHeroes do MarvelService
        ApiService.service.getHeroes().enqueue(object : Callback<HeroesResponse> {

            override fun onResponse(call: Call<HeroesResponse>, response: Response<HeroesResponse>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        _heroesLiveData.value = it.data.results
                    }
                }
            }

            override fun onFailure(call: Call<HeroesResponse>, t: Throwable) {

            }

        })
    }

    fun getHeroesSelected(query: String){ // aplica o método enqueue a getHeroesSelected do MarvelService
        ApiService.service.getHeroesSelected(query).enqueue(object : Callback<HeroesResponse> {

            override fun onResponse(call: Call<HeroesResponse>, response: Response<HeroesResponse>) {
                if (response.isSuccessful){
                    response.body()?.let {heroesResponse ->
                        _heroesLiveData.value = heroesResponse.data.results
                    }
                }

            }
            override fun onFailure(call: Call<HeroesResponse>, t: Throwable) {

            }

        })
    }


}