package com.prabhatkushwaha.mychef.framework.presentation.ui.views

import java.util.*

class CalenderUtils {
    companion object {
        fun getGreetingMessage(): String {
            val c = Calendar.getInstance();
            val timeOfDay = c.get(Calendar.HOUR_OF_DAY);

            return if (timeOfDay in 0..11) {
                return "Good Morning"
            } else if (timeOfDay in 12..15) {
                "Good Afternoon"
            } else if (timeOfDay in 16..20) {
                "Good Evening"
            } else
                "Good Night"
        }
    }
}