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
import com.example.android.cookingwithkotlin.adapters.MyIngredientsAdapter
import com.example.android.cookingwithkotlin.classes.Ingredient
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
import io.realm.*

/**
 * A simple [Fragment] subclass.
 */
class MyIngredientsFragment : Fragment(), RealmChangeListener<RealmResults<Ingredient>> {
    companion object {
        val TAG: String = MyIngredientsFragment::class.java.simpleName
    }

    private var adapter: MyIngredientsAdapter by Delegates.notNull()
    private var realm: Realm by Delegates.notNull()
    private var myIngredients: RealmResults<Ingredient> by Delegates.notNull()
    override fun onCreate(args: Bundle?) {
        super.onCreate(args)
        retainInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_myingredients, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientList.layoutManager = LinearLayoutManager(context)
    }


    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()
        getRealm()
    }

    fun getRealm() {
        myIngredients = realm.where(Ingredient::class.java).findAllSortedAsync("name",Sort.DESCENDING)
        adapter = MyIngredientsAdapter(myIngredients) {}
        myIngredients.addChangeListener(RealmChangeListener<RealmResults<Ingredient>> {})
        ingredientList.adapter = adapter
        Log.v(TAG, myIngredients.toString())
    }

    override fun onChange(element: RealmResults<Ingredient>?) {
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }
}