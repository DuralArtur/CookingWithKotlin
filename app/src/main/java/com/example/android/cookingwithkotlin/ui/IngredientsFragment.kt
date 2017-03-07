package com.example.android.cookingwithkotlin.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.android.cookingwithkotlin.R
import kotlinx.android.synthetic.main.fragment_ingredients.*
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 */
class IngredientsFragment : Fragment() {
    companion object {
        val TAG: String = IngredientsFragment::class.java.simpleName
    }

    private var ingMenu: Array<String> by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_ingredients, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingMenu = resources.getStringArray(R.array.ingredients_menu)

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ingMenu)

        ingredientsMenuListView.adapter = adapter
        ingredientsMenuListView.setOnItemClickListener {
            adapterView, view, i, l ->
            swapFragment(ingredientsMenuListView.getItemAtPosition(i).toString())
        }
    }

    private fun swapFragment(name: String) {
        var fr: Fragment
        when (name) {
            ingMenu[0] -> fr = MyIngredientsFragment()
            ingMenu[1] -> fr = APISearchFragment()
            ingMenu[2] -> fr = AddIngredientFragment()
            else -> return
        }
            fragmentManager?.beginTransaction()?.replace(R.id.containerLayout, fr)?.addToBackStack(null)?.commit()
    }

}


