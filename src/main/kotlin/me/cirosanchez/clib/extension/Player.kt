package me.cirosanchez.clib.extension

import me.cirosanchez.clib.placeholder.Placeholder
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

fun Player.replacePlaceholdersForString(string: String, vararg placeholder: Placeholder): String {
    var string = PlaceholderAPI.setPlaceholders(this, string)
    string = string.placeholders(*placeholder)
    return string
}