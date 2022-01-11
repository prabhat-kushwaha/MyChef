package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.viewbinding.ViewBinding
import com.prabhatkushwaha.mychef.HomeActivity
import com.prabhatkushwaha.mychef.framework.UIController
import com.prabhatkushwaha.mychef.framework.presentation.ui.views.CustomProgressDialog
import kotlinx.coroutines.InternalCoroutinesApi

abstract class BaseFragment<B : ViewBinding> : Fragment() {


    companion object {
        const val TAG = "BaseFragment"
    }

    val progressDialog = CustomProgressDialog()
    lateinit var binding: B
    lateinit var mContext: Context
    lateinit var uiController: UIController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater, container)
        context?.let {
            mContext = it
        }
        subscribeObservers()
        initialize()
        return binding.root
    }

    abstract fun initialize()

    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): B


    fun showToastMessage(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    abstract fun subscribeObservers()

    fun showProgressDialog() {
        progressDialog.show(mContext)
    }

    fun hideProgressDialog() {
        progressDialog.dialog.dismiss()
    }
}

















