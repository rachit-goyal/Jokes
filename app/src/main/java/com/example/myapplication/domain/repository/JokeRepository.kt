package com.example.myapplication.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.retrofit.JokesAPI
import com.example.myapplication.data.db.JokeDataBase
import com.example.myapplication.data.model.Jokes
import javax.inject.Inject

class JokeRepository @Inject constructor(
    val api: JokesAPI,
    val jokesDataBase: JokeDataBase
) {

    private val _jokes = MutableLiveData<Jokes>()
    val jokes: LiveData<Jokes>
        get() = _jokes

    private val _jokesList = MutableLiveData<List<Jokes>>()
    val jokesList: LiveData<List<Jokes>>
        get() = _jokesList

    suspend fun getJokes() {
        val result = api.getData("json");
        if (result?.body() != null) {
            _jokes.postValue(result.body())
        }

    }

    suspend fun addJokes(list: List<Jokes>) {
        jokesDataBase.jokeDAO().addJoke(list)

    }

    suspend fun deleteData() {
        jokesDataBase.jokeDAO().deleteData()
    }

    suspend fun getLocalData() {
        val quotes = jokesDataBase.jokeDAO().getJokes()
        _jokesList.postValue(quotes)
    }
}