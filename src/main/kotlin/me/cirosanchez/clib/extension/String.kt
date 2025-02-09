package me.cirosanchez.clib.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import me.cirosanchez.clib.placeholder.Placeholder
import org.bukkit.command.CommandSender


fun String.colorize(): Component {
    val msg = ChatColor.translateAlternateColorCodes('&', this)
    return MiniMessage.miniMessage().deserialize(this)
}

fun String.placeholders(vararg placeholders: Placeholder): String {
    var stringo = this

    for (placeholder in placeholders){
        stringo = stringo.replace(placeholder.target, placeholder.replacement)
    }
    return stringo
}

fun String.sendToCommandSender(commandSender: CommandSender, vararg placeholders: Placeholder){
    commandSender.send(this.placeholders(*placeholders))
}

/*
Taken from flytegg/twilight

 */

val IRREGULAR_NOUNS = mapOf(
    "man" to "men",
    "woman" to "women",
    "child" to "children",
    "tooth" to "teeth",
    "foot" to "feet",
    "mouse" to "mice",
    "person" to "people",
    "goose" to "geese",
    "ox" to "oxen",
    "leaf" to "leaves",
    "sheep" to "sheep",
    "deer" to "deer",
    "fish" to "fish",
    "moose" to "moose",
    "aircraft" to "aircraft",
    "hovercraft" to "hovercraft",
    "spacecraft" to "spacecraft",
    "watercraft" to "watercraft",
    "offspring" to "offspring",
    "species" to "species",
    "series" to "series",
)

fun String.pluralize(): String = IRREGULAR_NOUNS[this]?.let { return it } ?: when {
    endsWith("y") && length > 1 && !this[lastIndex - 1].isVowel() -> dropLast(1) + "ies"
    endsWith("us") -> dropLast(2) + "i"  // Latin origin, e.g., "cactus" to "cacti"
    endsWith("is") -> dropLast(2) + "es" // Greek origin, e.g., "analysis" to "analyses"
    endsWith("ch") || endsWith("sh") || endsWith("x") || endsWith("s") || endsWith("z") -> this + "es"
    else -> this + "s"
}

enum class Case {
    CAMEL,
    SNAKE,
    PASCAL,
}
var CASE_DELIMITER_REGEX = Regex("(?<!^)(?=[A-Z])|[_\\-\\s]+")


fun String.formatCase(case: Case): String = CASE_DELIMITER_REGEX.split(this)
    .filter { it.isNotEmpty() }
    .map { it.lowercase() }
    .run {
        when (case) {
            Case.CAMEL -> mapIndexed { index, word ->
                if (index == 0) word else word.capitalizeFirstLetter()
            }.joinToString("")

            Case.SNAKE -> joinToString("_")

            Case.PASCAL -> joinToString("") { it.capitalizeFirstLetter() }
        }
    }

fun String.capitalizeFirstLetter(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}