package com.prabhatkushwaha.mychef.business.domain.state

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*

abstract class DataChannelManager<ViewState> {

    var channelScope: CoroutineScope? = null

    val messageStack = MessageStack()

    val stateEventManager = StateEventManager()

    val shouldDisplayProgressDialog = stateEventManager.shouldDisplayProgressDialog
    fun setupChannel() {
        cancelJobs()
    }

    fun printStateMessages() {
        for (message in messageStack) {
            Log.d("DCM", "${message.response.message}")
        }
    }

    fun cancelChannelScope() {
        if (channelScope != null) {
            if (channelScope?.isActive == true)
                channelScope?.cancel()
            channelScope = null
        }

    }

    abstract fun handleNewData(data: ViewState)

    @InternalCoroutinesApi
    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<ViewState>?>,
        viewModelScope: CoroutineScope
    ) {
        viewModelScope.launch {
            jobFunction.collect {

            }
        }
        if (canExecuteNewStateEvent(stateEvent)) {
            addStateEvent(stateEvent)

            getChannelScope().launch {
                jobFunction.collect { it ->
                    it?.let {
                        withContext(Main) {
                            it.data?.let {
                                handleNewData(it)
                            }
                            it.stateMessage?.let {

                            }
                            it.stateEvent?.let {
                                removeStateEvent(stateEvent)
                            }
                        }
                    }
                }
            }



            jobFunction
                .onEach { dataState ->
                    Log.d("TAG", "launchJob: $dataState")
                    dataState?.let {
                        withContext(Main) {
                            dataState.data?.let { data ->
                                handleNewData(data)
                            }
                            dataState.stateMessage?.let { stateMessage ->
                                addStateMessage(stateMessage)
                            }
                            dataState.stateEvent?.let { stateEvent ->
                                removeStateEvent(stateEvent)
                            }
                        }
                    }
                }.launchIn(getChannelScope())


        }

    }


    fun canExecuteNewStateEvent(stateEvent: StateEvent): Boolean {

        if (isJobAlreadyActive(stateEvent)) {
            return false
        }
        if (!isMessageStackEmpty()) {
            if (messageStack[0].response.uiComponent == UIComponentTypes.Dialog()) {
                return false
            }
        }
        return true
    }

    fun isMessageStackEmpty() = messageStack.isStackEmpty()

    fun addStateMessage(stateMessage: StateMessage) = messageStack.add(stateMessage)

    fun removeStateMessage(position: Int = 1) = messageStack.removeAt(position)

    fun printAllStateMessage() {
        for (message in messageStack) {
            Log.d("TAG", "printAllStateMessage: ${message.response.message}")
        }
    }

    fun getActiveJobs() = stateEventManager.getActiveJobName()

    fun removeStateEvent(stateEvent: StateEvent?) =
        stateEventManager.removeStateEvent(stateEvent)

    fun isJobAlreadyActive(stateEvent: StateEvent) =
        stateEventManager.isActiveStateEvent(stateEvent)

    @JvmName("getChannelScope1")
    fun getChannelScope() = channelScope ?: setUpNewChannelScope(CoroutineScope(IO))

    fun setUpNewChannelScope(coroutineScope: CoroutineScope): CoroutineScope {
        channelScope = coroutineScope
        return coroutineScope
    }

    fun addStateEvent(stateEvent: StateEvent) = stateEventManager.addStateEvent(stateEvent)

    fun clearActiveStateEvents() = stateEventManager.clearActiveStateEvent()

    fun cancelJobs() {
        if (channelScope != null) {
            if (channelScope?.isActive == true) {
                channelScope?.cancel()
            }
            channelScope = null
        }
        clearActiveStateEventCounter()
    }

    fun clearActiveStateEventCounter() {
        stateEventManager.clearActiveStateEvent()
        stateEventManager.synNumActiveStateEvent()
    }

}