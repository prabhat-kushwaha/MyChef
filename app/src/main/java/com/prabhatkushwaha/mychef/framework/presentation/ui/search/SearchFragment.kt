package com.prabhatkushwaha.mychef.framework.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prabhatkushwaha.mychef.R


class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mView =
            inflater.inflate(
                R.layout.fragment_search,
                container,
                false
            )

        return mView
    }
}