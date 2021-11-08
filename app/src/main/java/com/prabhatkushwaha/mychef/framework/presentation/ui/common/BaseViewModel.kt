package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prabhatkushwaha.mychef.business.data.network.utils.INVALID_STATE_EVENT
import com.prabhatkushwaha.mychef.business.domain.state.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel<ViewState> : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()

    companion object {
        const val TAG = "BaseViewModel"
    }

    val viewState: LiveData<ViewState> get() = _viewState

    private val dataChannelManager: DataChannelManager<ViewState> =
        object : DataChannelManager<ViewState>() {
            override fun handleNewData(data: ViewState) {
                this@BaseViewModel.handleNewData(data)
            }
        }

    val shouldDisplayProgress: LiveData<Boolean> = dataChannelManager.shouldDisplayProgressDialog

    val stateMessage: LiveData<StateMessage?> get() = dataChannelManager.messageStack.stateMessage

    fun getMessageStackSize(): Int {
        return dataChannelManager.messageStack.size
    }

    fun setupChannel() = dataChannelManager.setupChannel()

    abstract fun setStateEvent(stateEvent: StateEvent)

    abstract fun handleNewData(viewState: ViewState)

    fun emitStateMessageEvent(
        stateMessage: StateMessage,
        stateEvent: StateEvent
    ) = flow {
        emit(
            DataState.error<ViewState>(
                response = stateMessage.response,
                stateEvent = stateEvent
            )
        )
    }

    fun emitInvalidStateEvent(stateEvent: StateEvent) = flow {
        emit(
            DataState.error<ViewState>(
                response = Response(
                    message = INVALID_STATE_EVENT,
                    uiComponent = UIComponentTypes.None(),
                    messageType = MessageType.Error()
                ),
                stateEvent = stateEvent
            )
        )
    }

    @InternalCoroutinesApi
    fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataState<ViewState>?>,
    ) = dataChannelManager.launchJob(stateEvent, jobFunction,viewModelScope)

    fun getCurrentViewStateOrNew(): ViewState {
        return viewState.value ?: initNewViewState()
    }

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState!!
    }


    fun clearStateMessage(index: Int = 0) {
        dataChannelManager.messageStack.removeAt(index)
    }

    fun clearActiveStateEvents() = dataChannelManager.clearActiveStateEventCounter()

    fun clearAllStateMessages() = dataChannelManager.messageStack.clear()

    fun printStateMessages() = dataChannelManager.printStateMessages()

    fun cancelActiveJobs() = dataChannelManager.cancelJobs()

    abstract fun initNewViewState(): ViewState


}