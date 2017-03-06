package com.example.android.cookingwithkotlin.realmClass

import io.realm.RealmObject

/**
 * Created by Artur on 02-Mar-17.
 */
open class Step : RealmObject() {
    open var description: String? = null
}