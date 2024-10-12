package me.cirosanchez.clib.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import me.cirosanchez.clib.placeholder.Placeholder
import org.bukkit.command.CommandSender


fun String.colorize(): Component {
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