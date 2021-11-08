package com.prabhatkushwaha.mychef.business.domain.state


data class StateMessage(val response: Response)


data class Response(
    val message: String?,
    val uiComponent: UIComponentTypes,
    val messageType: MessageType
)


sealed class UIComponentTypes {

    class Toast : UIComponentTypes()

    class Dialog : UIComponentTypes()

    class None : UIComponentTypes()

}

interface StateMessageCallback{

    fun removeMessageFromStack()
}

sealed class MessageType {

    class Error : MessageType()

    class Info : MessageType()

    class Success : MessageType()

    class None : MessageType()
}