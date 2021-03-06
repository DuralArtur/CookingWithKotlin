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

/**
 * A simple [Fragment] subclass.
 */
class APISearchFragment : Fragment() {
    companion object {
        val TAG: String = APISearchFragment::class.java.simpleName
    }

    private var foodsList: SearchResult by Delegates.notNull()
    private var realm: Realm by Delegates.notNull()
    override fun onCreate(args: Bundle?) {
        super.onCreate(args)
        retainInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_apisearch, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientList.layoutManager = LinearLayoutManager(context)
        search.onQueryText({
            getFood(it)
            true
        })
    }


    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()

    }

    fun getFood(food: String) {
        val foodManager: FoodManager = FoodManager()
        doAsync {
            foodsList = foodManager.getFoods(food)
            uiThread {
                val adapter = APISearchAdapter(foodsList.list.item) {
                }
                ingredientList.adapter = adapter
            }
        }
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }
}