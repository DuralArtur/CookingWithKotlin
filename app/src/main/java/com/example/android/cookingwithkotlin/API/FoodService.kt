package com.example.android.cookingwithkotlin.API

import com.example.android.cookingwithkotlin.classes.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Query

/**
 * Created by Artur on 03-Mar-17.
 */
interface FoodService {
    @GET("ndb/search/?format=json&sort=r&api_key=7V221EgieT7cPuTTn9zcSTV1ziLtjsScvxyIEGTW")
    fun getFood(@Query("q") food:String): Call<SearchResult>
}
