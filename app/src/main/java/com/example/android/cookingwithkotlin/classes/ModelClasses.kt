package com.example.android.cookingwithkotlin.classes

/**
 * Created by Artur on 03-Mar-17.
 */
data class SearchResult(val list: Lists)
data class Lists(val q: String, val item: List<Food>)
data class Food(val name:String, val ndbno: Long)