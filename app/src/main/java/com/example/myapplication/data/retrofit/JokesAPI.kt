package com.example.myapplication.data.retrofit

import com.example.myapplication.data.model.Jokes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesAPI {
    @GET("api")
    suspend fun getData(@Query("format") format:String):Response<Jokes>?
}