package me.cirosanchez.clib.extension

import me.cirosanchez.clib.CLib
import me.cirosanchez.clib.logger
import org.bukkit.command.CommandSender
import java.util.logging.Logger

fun CommandSender.send(message: String){
    CLib.get().audiences.sender(this).sendMessage(message.colorize())
}