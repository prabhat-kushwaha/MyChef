package com.prabhatkushwaha.mychef.framework.presentation.ui.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.AuthActivity
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentSignUpBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.getAfterChangeText
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalPagingApi
@InternalCoroutinesApi
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding>() {


    private var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initialize() {

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

        binding.etPassword.getAfterChangeText {
            if (it.matches(".*\\d.*".toRegex())) {
                binding.tvContainNo.compoundDrawables[0].mutate()
                    .setTint(mContext.getColor(R.color.primaryColor))
                binding.tvContainNo.setTextColor(mContext.getColor(R.color.primaryColor))
            } else {
                binding.tvContainNo.compoundDrawables[0].mutate()
                    .setTint(mContext.getColor(R.color.secondary_textColor))
                binding.tvContainNo.setTextColor(mContext.getColor(R.color.secondary_textColor))
            }
            if (it.length >= 6) {
                binding.tvCharCount.compoundDrawables[0].mutate()
                    .setTint(mContext.getColor(R.color.primaryColor))
                binding.tvCharCount.setTextColor(mContext.getColor(R.color.primaryColor))
            } else {
                binding.tvCharCount.compoundDrawables[0].mutate()
                    .setTint(mContext.getColor(R.color.secondary_textColor))
                binding.tvCharCount.setTextColor(mContext.getColor(R.color.secondary_textColor))
            }

        }

        binding.btSignUp.setOnClickListener {
            val activity: AuthActivity = AuthActivity()
            activity.signUpWithEmailAndPassword(
                binding.etEmail.getValue().trim(),
                binding.etPassword.getValue().trim()
            ) { isCreated, message ->
                Log.d(TAG, "initialize: $message")
                if (isCreated)
                    showToastMessage("your account has been created successfully")
                else if (!isCreated && message != null)
                    showToastMessage(message)
                else
                    showToastMessage("an error occurred while creating your account")
            }
        }


    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun subscribeObservers() {

    }
}