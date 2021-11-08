package com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prabhatkushwaha.mychef.R
import javax.inject.Inject


class OnBoardingFragment @Inject constructor(factory:ViewModelProvider.Factory) : Fragment() {

    val viewModel:OnBoardingViewModel by viewModels {
        factory
    }

    var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView =
            inflater.inflate(
                R.layout.fragment_onboarding,
                container,
                false
            )
        mView.findViewById<Button>(R.id.btGetStarted).setOnClickListener {
           // viewModel.setNewUser()
            navController?.navigate(R.id.action_onBoardingFragment_to_signInFragment)
        }
        return mView
    }
}