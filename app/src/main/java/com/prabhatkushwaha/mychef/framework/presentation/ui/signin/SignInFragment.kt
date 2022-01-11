package com.prabhatkushwaha.mychef.framework.presentation.ui.signin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.AuthActivity
import com.prabhatkushwaha.mychef.HomeActivity
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentSignInBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview as FlowPreview1

@ExperimentalCoroutinesApi
@FlowPreview1
@ExperimentalPagingApi
@InternalCoroutinesApi
class SignInFragment @Inject constructor(factory: ViewModelProvider.Factory) :
    BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by viewModels {
        factory
    }

    private var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initialize() {

        binding.tvSignUp.setOnClickListener {
            navController?.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.ivPasswordVisible.setOnClickListener {
            if (binding.etPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivPasswordVisible.setImageDrawable(mContext.getDrawable(R.drawable.ic_invisible))
            } else {
                binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.ivPasswordVisible.setImageDrawable(mContext.getDrawable(R.drawable.ic_visible))
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }

        val activity = AuthActivity()

        binding.btSignIn.setOnClickListener {
            activity.singInWithEmailAndPassword(
                binding.etEmail.getValue(),
                binding.etPassword.getValue()
            ) { loggedIn, user ->
                if (loggedIn) {
                    showToastMessage("logged in successfully")
                    if (user != null && viewModel.saveSignInUserData(user)) {
                        startActivity(Intent(context, HomeActivity::class.java))
                    }
                } else
                    showToastMessage("login failed")
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            activity.sendPasswordResetMail(
                binding.etEmail.getValue().trim()
            ) { status ->
                if (status)
                    showToastMessage("Password reset email sent successfully")
                else
                    showToastMessage("Please enter valid email id")
            }
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun subscribeObservers() {
    }
}