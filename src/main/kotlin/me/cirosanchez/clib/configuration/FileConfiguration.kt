package me.cirosanchez.clib.configuration

class FileConfiguration(val string: String): Configuration() {
    override fun getFileName(): String {
        return string;
    }
}