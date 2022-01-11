package com.prabhatkushwaha.mychef

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import com.fatsecret.platform.model.CompactFood
import com.fatsecret.platform.model.CompactRecipe
import com.fatsecret.platform.model.Food
import com.fatsecret.platform.model.Recipe
import com.fatsecret.platform.services.Response
import com.fatsecret.platform.services.android.ResponseListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.AppFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import com.android.volley.toolbox.Volley
import com.prabhatkushwaha.mychef.framework.presentation.ui.home.HomeFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.RecipeDetailsFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInFragment


@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalPagingApi
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }

    fun updateStatusBarColor(color: Int) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

    fun getStatusBarColor(): Int {
        val window: Window = window
        return window.statusBarColor
    }

    fun enableNavigationBar() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    fun disableNavigationBar() {
        bottomNavigationView.visibility = View.GONE
    }
    /* override fun onBackPressed() {
         val navHostFragment: NavHostFragment =
             supportFragmentManager.findFragmentById(R.id.app_nav_host_fragment) as NavHostFragment
         val fragment = navHostFragment.childFragmentManager.fragments[0]
         val navController=navHostFragment.navController
         if (fragment is RecipeDetailsFragment) {
             navController.navigate(R.id.action_recipeDetailsFragment_to_homeFragment)
         } else super.onBackPressed()
     }*/

    fun changeToolBarPrevColor() {
        when (resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                val window: Window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = getColor(R.color.transparent)
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                val window: Window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = getColor(R.color.whiteColor)
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                val window: Window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = getColor(R.color.transparent)
            }
        }
    }

}