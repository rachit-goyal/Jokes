package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.model.Jokes

@Dao
interface JokeDAO {

    @Insert
    suspend fun addJoke(jokes: List<Jokes>)

    @Query("DELETE FROM jokes")
    suspend fun deleteData();

    @Query("SELECT * FROM jokes")
    suspend fun getJokes() : List<Jokes>
}