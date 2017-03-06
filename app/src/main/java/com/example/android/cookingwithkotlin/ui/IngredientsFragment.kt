package com.example.android.cookingwithkotlin.ui


import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.android.cookingwithkotlin.R
import com.example.android.cookingwithkotlin.R.string.ingredients
import kotlinx.android.synthetic.main.fragment_ingredients.*
import java.text.FieldPosition
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 */
class IngredientsFragment : Fragment() {
    companion object {
        val TAG: String = IngredientsFragment::class.java.simpleName
    }

    val ingMenu = resources.getStringArray(R.array.ingredients_menu)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_ingredients, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adaptek = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ingMenu)

        ingredientsMenuListView.adapter = adaptek
//        ingredientsMenuListView.setOnItemClickListener {
//            adapterView, view, i, l ->
//            swapFragment(ingredientsMenuListView.getItemAtPosition(i).toString())
//        }
    }

    private fun swapFragment(name: String) {
        var fr: Fragment
        when (name) {
            ingMenu[0] -> fr = APISearchFragment()
            ingMenu[1] -> fr = APISearchFragment()
            ingMenu[2] -> fr = APISearchFragment()
            else -> fr = APISearchFragment()
        }
            fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, fr)?.addToBackStack(null)?.commit()
    }

}


