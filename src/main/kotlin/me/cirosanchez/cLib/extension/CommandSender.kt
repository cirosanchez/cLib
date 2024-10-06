package me.cirosanchez.cLib.extension

import me.cirosanchez.cLib.CLib
import org.bukkit.command.CommandSender

fun CommandSender.send(message: String){
    CLib.audiences.sender(this).sendMessage(message.colorize())
}