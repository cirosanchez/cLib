package me.cirosanchez.cLib.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import me.cirosanchez.cLib.placeholder.Placeholder
import org.bukkit.command.CommandSender


fun String.colorize(): Component {
    return MiniMessage.miniMessage().deserialize(this)
}

fun String.placeholders(vararg placeholders: Placeholder): String {
    val stringBuilder = StringBuilder(this)

    for (placeholder in placeholders) {
        val start = stringBuilder.indexOf(placeholder.target)
        while (start != -1) {
            stringBuilder.replace(start, start + placeholder.target.length, placeholder.replacement)
        }
    }
    return stringBuilder.toString()
}

fun String.sendToCommandSender(commandSender: CommandSender, vararg placeholders: Placeholder){
    commandSender.send(this.placeholders(*placeholders))
}