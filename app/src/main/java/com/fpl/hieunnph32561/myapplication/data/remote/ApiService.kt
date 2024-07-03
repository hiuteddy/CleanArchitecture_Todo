package com.fpl.hieunnph32561.myapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private const val BASE_URL = "http://10.0.3.2:3000/todos/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val todoApi: TodoApi = retrofit.create(TodoApi::class.java)
}
