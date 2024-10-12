package me.cirosanchez.clib.extension

import me.cirosanchez.clib.CLib
import me.cirosanchez.clib.logger
import me.cirosanchez.clib.placeholder.Placeholder
import org.bukkit.command.CommandSender
import java.util.logging.Logger

fun CommandSender.send(message: String){
    CLib.get().audiences.sender(this).sendMessage(message.colorize())
}

fun CommandSender.send(path: String, vararg placeholder: Placeholder){
    if(!CLib.get().messages) {
        logger().warning("You didn't setup messages in cLib main function! In order to use integrated messages.yml use it.")
        return
    }

    val string = CLib.get().messagesFile.getString(path) ?: "NULL"
    CLib.get().audiences.sender(this).sendMessage(string.placeholders(*placeholder).colorize())
}