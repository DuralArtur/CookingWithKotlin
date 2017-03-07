package com.example.android.cookingwithkotlin.classes

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Artur on 02-Mar-17.
 */
open class Amount : RealmObject() {
    open var amount : Double = 0.0
}

open class Ingredient(
        open var name: String = "",
        open var protein: Int = 0,
        open var carbs: Int = 0,
        open var fat: Int = 0,
        open var kcal: Int = 0
) : RealmObject()

open class Recipe(
        @PrimaryKey open var id: Long = 0,
        open var recipeName: String = "",
        open var steps: RealmList<Step> = RealmList(),
        open var ingredients: RealmList<Ingredient> = RealmList(),
        open var amounts: RealmList<Amount> = RealmList()
) : RealmObject()

open class Step : RealmObject() {
    open var description: String? = null
}