package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.db.JokeDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context : Context) : JokeDataBase{
        return Room.databaseBuilder(context, JokeDataBase::class.java, "jokesDB").build()
    }
}