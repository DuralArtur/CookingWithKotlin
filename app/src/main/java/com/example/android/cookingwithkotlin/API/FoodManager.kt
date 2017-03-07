package com.example.android.cookingwithkotlin.API

import com.example.android.cookingwithkotlin.classes.SearchResult

/**
 * Created by Artur on 03-Mar-17.
 */
class FoodManager(private val api: FoodAPI = FoodAPI()) {
    fun getFoods(ingredient: String = "chicken") : SearchResult {
        val response = api.getFood(ingredient).execute()
        return response.body()
    }
}