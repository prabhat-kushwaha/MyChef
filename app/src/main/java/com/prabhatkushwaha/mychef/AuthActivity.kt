package com.prabhatkushwaha.mychef

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.AppFragmentFactory
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {


    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        Log.d("TAG", "onBackPressed: $fragment")
        if (fragment is SignInFragment) {
            //exitConfirmation
            super.onBackPressed()

        } else super.onBackPressed()
    }


}