package com.prabhatkushwaha.mychef.framework.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.HomeActivity
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentSplashBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@SuppressLint("CustomSplashScreen")
@InternalCoroutinesApi
@FlowPreview
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class SplashScreenFragment(private val factory: ViewModelProvider.Factory) :
    BaseFragment<FragmentSplashBinding>() {


    private val viewModel: SplashScreenViewModel by viewModels {
        factory
    }

    private var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }


    override fun initialize() {
        Handler(Looper.getMainLooper()).postDelayed({
            //if (viewModel.isSignIn())
            startActivity(Intent(context, HomeActivity::class.java))
            //else if (viewModel.isNewUser())
            //  navController?.navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
            //else if (!viewModel.isNewUser())
            //     navController?.navigate(R.id.action_splashScreenFragment_to_signInFragment)
        }, 1000)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun subscribeObservers() {

    }
}