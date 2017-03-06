package com.example.android.cookingwithkotlin.realmClass

import io.realm.RealmObject

/**
 * Created by Artur on 02-Mar-17.
 */
open class Amount : RealmObject() {
    open var amount : Double = 0.0
}