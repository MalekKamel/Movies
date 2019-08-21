package com.movie.app.shared.ext

import com.movie.app.shared.util.app.MyApp


fun String.withTitle(title: String): String {
    return "$title: $this"
}

fun String?.withTitleRes(title: Int): String {
    return "${MyApp.context.getString(title)}: $this"
}

fun String.withSuffix(suffix: String): String {
    return "$this $suffix"
}

fun String.withSuffixRes(suffix: Int): String {
    return "$this ${MyApp.context.getString(suffix)}"
}

fun String.withPrefix(suffix: String): String {
    return "$suffix $this"
}

fun String.withPrefixRes(suffix: Int): String {
    return "${MyApp.context.getString(suffix)} $this"
}