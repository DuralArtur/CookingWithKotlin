package com.example.android.cookingwithkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.cookingwithkotlin.classes.Food
import com.example.android.cookingwithkotlin.classes.Lists
import com.example.android.cookingwithkotlin.R
import com.example.android.cookingwithkotlin.classes.Ingredient
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.android.synthetic.main.ingredient_item.view.*

/**
 * Created by Artur on 03-Mar-17.
 */
class APISearchAdapter(val foodsList: List<Food>, val itemClick: (Food) -> Unit): RecyclerView.Adapter<APISearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.ingredient_item,parent,false)
        return  ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindIngredients(foodsList[position])
    }

    override fun getItemCount() = foodsList.size

    class ViewHolder(view: View, val itemClick: (Food) -> Unit) : RecyclerView.ViewHolder(view){

        fun bindIngredients(food: Food){
            with(food.name){
                itemView.ingredientName.text = food.name
            }
        }
    }
}