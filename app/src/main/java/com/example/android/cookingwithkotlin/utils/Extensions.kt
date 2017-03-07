package com.example.android.cookingwithkotlin.utils

import android.support.v7.widget.SearchView
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnClickListener
import android.widget.ListView
import android.widget.Toast


/**
 * Created by Artur on 03-Mar-17.
 */

fun SearchView.onQueryText(submit: (String) -> Boolean = { false }, textChange: (String) -> Boolean = { false }) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = submit(query)
        override fun onQueryTextChange(newText: String): Boolean = textChange(newText)
    })
}


