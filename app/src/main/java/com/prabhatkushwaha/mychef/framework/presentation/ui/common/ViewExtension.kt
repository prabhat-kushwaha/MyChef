package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

fun EditText.getValue() = this.text.toString()


fun EditText.getAfterChangeText(text: (data: String) -> Unit){
    this.addTextChangedListener(object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            text.invoke(p0.toString())
        }
    })
}