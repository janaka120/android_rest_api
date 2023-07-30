package com.example.retrofitapplication

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/posts")
    fun getAllPosts() : Call<List<Posts>>
}