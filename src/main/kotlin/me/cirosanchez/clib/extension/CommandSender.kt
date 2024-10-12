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
    val messages = CLib.get().messagesFile ?: throw Exception("You didn't enable messages while setting up cLib!")

    val string = messages.getString(path) ?: "NULL"
    CLib.get().audiences.sender(this).sendMessage(string.placeholders(*placeholder).colorize())
}