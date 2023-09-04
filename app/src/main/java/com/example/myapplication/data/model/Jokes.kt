package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class Jokes(
    @PrimaryKey(autoGenerate = true)
    val jokeId: Int,
    val joke: String?
)