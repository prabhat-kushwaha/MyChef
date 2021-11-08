package com.prabhatkushwaha.mychef.framework

import android.webkit.WebMessagePort
import com.prabhatkushwaha.mychef.business.domain.state.Response
import com.prabhatkushwaha.mychef.business.domain.state.StateMessage
import com.prabhatkushwaha.mychef.business.domain.state.StateMessageCallback

interface UIController {
    fun displayProgressBar(value: Boolean)

    fun hideSoftKeyboard()


    fun onResponseReceived(
        response: Response,
        stateMessageCallback: StateMessageCallback
    )
}