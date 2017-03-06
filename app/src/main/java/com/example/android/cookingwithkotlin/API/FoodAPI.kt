package com.example.android.cookingwithkotlin.API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Artur on 03-Mar-17.
 */
class FoodAPI {
    private val foodService: FoodService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.nal.usda.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        foodService = retrofit.create(FoodService::class.java)
    }
    fun getFood(name: String): Call<SearchResult> {return foodService.getFood(name)}
}
