package me.cirosanchez.clib.placeholder

data class Placeholder(val target: String, val replacement: String){
    fun replace(string: String): String {
        return string.replace(target, replacement)
    }
}
