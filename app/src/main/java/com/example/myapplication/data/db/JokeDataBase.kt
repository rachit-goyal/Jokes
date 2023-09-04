package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.Jokes


@Database(entities = [Jokes::class], version = 1)
abstract class JokeDataBase : RoomDatabase() {

        abstract fun jokeDAO() : JokeDAO

}