package com.example.android.cookingwithkotlin.realmClass

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Artur on 02-Mar-17.
 */

open class Ingredient(
        open var name: String = "",
        open var protein: Int = 0,
        open var carbs: Int = 0,
        open var fat: Int = 0,
        open var kcal: Int = 0
) : RealmObject() {}