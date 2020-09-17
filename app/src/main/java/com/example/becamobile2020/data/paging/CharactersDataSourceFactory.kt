package com.example.becamobile2020.data.paging

import com.example.becamobile2020.data.MarvelServices
import com.example.becamobile2020.data.response.Character
import io.reactivex.disposables.CompositeDisposable
import javax.sql.DataSource

class CharactersDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val marvelServices: MarvelServices
) : androidx.paging.DataSource.Factory<Int, Character>() {

    override fun create(): androidx.paging.DataSource<Int, Character> {
        return CharactersDataSource(marvelServices,compositeDisposable)
    }

}