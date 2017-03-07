package com.example.android.cookingwithkotlin.ui


import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.cookingwithkotlin.API.FoodManager
import com.example.android.cookingwithkotlin.R
import com.example.android.cookingwithkotlin.R.id.ingredientList
import com.example.android.cookingwithkotlin.R.id.search
import com.example.android.cookingwithkotlin.classes.SearchResult
import com.example.android.cookingwithkotlin.adapters.APISearchAdapter
import com.example.android.cookingwithkotlin.classes.Ingredient
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_apisearch.*
import org.jetbrains.anko.appcompat.v7.onQueryTextListener
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates
import com.example.android.cookingwithkotlin.utils.onQueryText
import kotlinx.android.synthetic.main.fragment_addingredient.*

/**
 * A simple [Fragment] subclass.
 */
class AddIngredientFragment : Fragment() {
    companion object {
        val TAG: String = AddIngredientFragment::class.java.simpleName
    }

    private var realm: Realm by Delegates.notNull()
    override fun onCreate(args: Bundle?) {
        super.onCreate(args)
        retainInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_addingredient, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()
        addIngButton.setOnClickListener {
            realm.executeTransaction {
                val ing = realm.createObject(Ingredient::class.java)
                ing.name = ingNameTV.text.toString()
                ing.protein = proteinTV.text.toString().toInt()
                ing.carbs = carbsTV.text.toString().toInt()
                ing.fat = fatTV.text.toString().toInt()
                ing.kcal = kcalTV.text.toString().toInt()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }
}