package com.example.android.cookingwithkotlin.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.cookingwithkotlin.R


/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment() {
    companion object {
        val TAG: String = FavouritesFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_favourites, container, false)
    }

}// Required empty public constructor
