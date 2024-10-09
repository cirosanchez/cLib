package me.cirosanchez.clib.extension

import me.cirosanchez.clib.CLib
import org.bukkit.command.CommandSender

fun CommandSender.send(message: String){
    CLib.audiences.sender(this).sendMessage(message.colorize())
}