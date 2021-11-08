package com.prabhatkushwaha.mychef.business.domain.state

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StateEventManager {
     val activeStateEvents = mutableMapOf<String, StateEvent>()

    private var _shouldDisplayProgressDialog = MutableLiveData<Boolean>()

    val shouldDisplayProgressDialog: LiveData<Boolean> get() = _shouldDisplayProgressDialog

    fun getActiveJobName() = activeStateEvents.keys

    fun clearActiveStateEvent() {
        activeStateEvents.clear()
        synNumActiveStateEvent()
    }

    fun addStateEvent(stateEvent: StateEvent) {
        activeStateEvents[stateEvent.stateEvent()] = stateEvent
        synNumActiveStateEvent()
    }


    fun isActiveStateEvent(stateEvent: StateEvent) =
        activeStateEvents.contains(stateEvent.stateEvent())


    fun removeStateEvent(stateEvent: StateEvent?) {
        Log.d("TAG", "removeStateEvent: $stateEvent")
        activeStateEvents.remove(stateEvent?.stateEvent())
        synNumActiveStateEvent()
    }


     fun synNumActiveStateEvent() {
        var enableProgressDialog: Boolean = false
        for (state in activeStateEvents.values) {
            if (state.shouldShowProgressDialog()) enableProgressDialog = true
        }
        _shouldDisplayProgressDialog.value = enableProgressDialog
    }
}
