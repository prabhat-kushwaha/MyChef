package com.prabhatkushwaha.mychef.business.domain.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MessageStack : ArrayList<StateMessage>() {

    private var _stateMessage = MutableLiveData<StateMessage?>()

    val stateMessage: LiveData<StateMessage?> get() = _stateMessage

    override
    fun add(element: StateMessage): Boolean {
        if (this.contains(element)) {
            return false
        }
        val transection = this.add(element)
        if (this.size == 1) {
            setStateMessage(element)
        }
        return transection
    }

    private fun setStateMessage(stateMessage: StateMessage?) {
        _stateMessage.value = stateMessage
    }

    override fun addAll(elements: Collection<StateMessage>): Boolean {
        for (element in elements) {
            add(element)
        }
        return true
    }

    override fun removeAt(index: Int): StateMessage {
        try {
            val transection = this.removeAt(index)
            if (this.size > 0) {
                setStateMessage(this[0])
            } else setStateMessage(null)
            return transection
        } catch (e: ArrayIndexOutOfBoundsException) {
            setStateMessage(null)
        }
        return StateMessage(Response("NONE", UIComponentTypes.Toast(), MessageType.None()))
    }

    override fun removeAll(elements: Collection<StateMessage>): Boolean {
        return super.removeAll(elements)
    }

    fun isStackEmpty(): Boolean {
        return size == 0
    }
}
