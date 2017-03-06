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
import com.example.android.cookingwithkotlin.SearchResult
import com.example.android.cookingwithkotlin.realmClass.Ingredient
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
import com.example.android.cookingwithkotlin.onQueryText

/**
 * A simple [Fragment] subclass.
 */
class APISearchFragment : Fragment(), RealmChangeListener<RealmResults<Ingredient>> {
    companion object {
        val TAG: String = APISearchFragment::class.java.simpleName
    }
    private var foodsList: SearchResult by Delegates.notNull()
    private var realm: Realm by Delegates.notNull()
    override fun onCreate(args: Bundle?) {
        super<Fragment>.onCreate(args)
        retainInstance
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_apisearch, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientList.layoutManager = LinearLayoutManager(context)
        search.onQueryText ({
            getFood(it)
            true
        })
        }


    override fun onResume() {
        super.onResume()
        realm = Realm.getDefaultInstance()

        }


    fun getFood(food: String){
        val foodManager: FoodManager = FoodManager()
        doAsync {
            foodsList = foodManager.getFoods(food)
            Log.d("CO TAM", foodsList.list.item[0].name)

            uiThread {
                realm.executeTransaction {
                    foodsList.list.item.forEach {
                        val ingredient = realm.createObject(Ingredient::class.java)
                        ingredient.name = it.name
                    }
                }

                loadIngredients()
            }
        }
    }

    fun loadIngredients() {

        val result = realm.where(Ingredient::class.java).findAllAsync()
        result.addChangeListener(RealmChangeListener<RealmResults<Ingredient>> {
            val adapter = APISearchAdapter(result) {

            }
            ingredientList.adapter = adapter
        })

    }

    override fun onChange(element: RealmResults<Ingredient>?) {
        Toast.makeText(context,"Co sie dzieje panie", Toast.LENGTH_SHORT)
    }
}// Required empty public constructor
