package com.prabhatkushwaha.mychef.business.domain.extensions

import java.lang.StringBuilder


fun List<String>?.getStringValue(): String {
    if (this == null) return ""
    val stringBuilder = StringBuilder()
    for (item in this) {
        if (stringBuilder.isEmpty())
            stringBuilder.append(item)
        else stringBuilder.append("||").append(item)
    }
    return stringBuilder.toString()
}

fun String?.getListValue(): List<String> {
    if (this == null) return listOf()
    val splitData = this.split("||")
    val dataset = ArrayList<String>()
    for (item in splitData) {
        dataset.add(item)
    }
    return dataset
}


fun String.round(): String {
    return this.subSequence(0, this.indexOf(".") + 2) as String
}