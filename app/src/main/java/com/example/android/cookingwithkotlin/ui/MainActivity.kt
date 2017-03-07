package com.example.android.cookingwithkotlin.ui

import android.app.Fragment
import android.app.ProgressDialog.show
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenu
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.android.cookingwithkotlin.R
import com.example.android.cookingwithkotlin.ui.RecipesFragment
import com.example.android.cookingwithkotlin.classes.Ingredient
import com.example.android.cookingwithkotlin.classes.Recipe
import com.example.android.cookingwithkotlin.classes.Step
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private var realm: Realm by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager?.beginTransaction()?.add(R.id.containerLayout, RecipesFragment())?.commit()
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.deleteAll()
        }
        basicCRUD(realm)
        val id = defaultSharedPreferences.getInt("selected_item", R.id.action_favourites)
        Log.v(TAG,resources.getInteger(id).toString())
        val menuItem = bottom_navigation.menu.findItem(id)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.menu.findItem(id).isChecked = true
        onNavigationItemSelected(menuItem)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_ingredients -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.containerLayout, IngredientsFragment())?.commit()
            }
            R.id.action_favourites -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.containerLayout, FavouritesFragment())?.commit()
            }
            R.id.action_recipes -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.containerLayout, RecipesFragment())?.commit()
            }
        }
        defaultSharedPreferences.edit().putInt("selected_item", item.itemId).apply()
        return true
    }

    private fun basicCRUD(realm: Realm) {
        realm.executeTransaction {
            val recipe = realm.createObject(Recipe::class.java, 0)
            recipe.recipeName = "Cwikla"
        }
        realm.executeTransaction {
            val recipe = realm.where(Recipe::class.java).findFirst()
            for (i in 0..1500) {
                val ingredient = realm.createObject(Ingredient::class.java)
                ingredient.name = "Kurczak_$i"

                val step = realm.createObject(Step::class.java)
                step.description = "dodej cwikle_$i"
                recipe.steps.add(step)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        realm.close()
    }
}
