package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prabhatkushwaha.mychef.business.domain.state.Response
import com.prabhatkushwaha.mychef.business.domain.state.StateMessageCallback
import com.prabhatkushwaha.mychef.business.domain.state.UIComponentTypes
import com.prabhatkushwaha.mychef.framework.UIController

abstract class BaseActivity : AppCompatActivity(), UIController {

    override fun displayProgressBar(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hideSoftKeyboard() {
        TODO("Not yet implemented")
    }

    override fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    ) {
        when (response.uiComponent) {
            is Toast -> {
                response.message?.let {
                    showToastMessage(response.message, stateMessageCallback)
                }

            }
            is UIComponentTypes.None -> {
                Log.i("TAG", "onResponseReceived: ${response.message}")
                stateMessageCallback.removeMessageFromStack()
            }

        }
    }

}