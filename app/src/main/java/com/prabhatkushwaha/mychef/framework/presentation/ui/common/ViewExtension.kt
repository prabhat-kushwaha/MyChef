package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import com.prabhatkushwaha.mychef.business.domain.state.StateMessageCallback


fun Activity.showToastMessage(
    message: String,
    stateMessageCallback: StateMessageCallback
) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    stateMessageCallback.removeMessageFromStack()
}