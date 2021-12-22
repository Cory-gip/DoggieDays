package com.example.phase3project2practice.network

import retrofit2.http.GET

interface DogPicApiService {

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Dog

}