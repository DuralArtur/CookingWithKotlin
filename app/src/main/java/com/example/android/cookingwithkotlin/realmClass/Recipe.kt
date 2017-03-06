package com.example.android.cookingwithkotlin.realmClass

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Artur on 02-Mar-17.
 */
open class Recipe(
        @PrimaryKey open var id: Long = 0,
        open var recipeName: String = "",
        open var steps: RealmList<Step> = RealmList(),
        open var ingredients: RealmList<Ingredient> = RealmList(),
        open var amounts: RealmList<Amount> = RealmList()
) : RealmObject() {
}