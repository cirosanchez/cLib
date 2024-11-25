package me.cirosanchez.clib.extension

/*
Taken from flytegg/twilight
 */

var VOWELS = listOf('a', 'e', 'i', 'o', 'u')

fun Char.isVowel(): Boolean = lowercaseChar() in VOWELS