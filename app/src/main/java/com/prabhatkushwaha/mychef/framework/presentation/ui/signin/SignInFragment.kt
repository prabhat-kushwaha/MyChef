package com.prabhatkushwaha.mychef.framework.presentation.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentSignInBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import javax.inject.Inject


class SignInFragment @Inject constructor(private val factory: ViewModelProvider.Factory) :
    BaseFragment<FragmentSignInBinding>() {


    private val viewModel: SignInViewModel by viewModels {
        factory
    }

    var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initialize() {
        binding.tvSignUp.setOnClickListener {
            navController?.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun subscribeObservers() {
        TODO("Not yet implemented")
    }
}