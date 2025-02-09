package me.cirosanchez.clib.extension

import me.cirosanchez.clib.CLib
import me.cirosanchez.clib.logger
import me.cirosanchez.clib.placeholder.Placeholder
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun CommandSender.send(message: String) {
    CLib.get().audiences.sender(this).sendMessage(message.colorize())
}

fun CommandSender.sendColorizedMessageFromMessagesFile(path: String, vararg placeholder: Placeholder) {
    if (!CLib.get().messages) {
        logger().warning("You didn't setup messages in cLib main function! In order to use integrated messages.yml use it.")
        return
    }

    var string = CLib.get().messagesFile.getString(path) ?: "NULL"

    if (this is Player){
        string = PlaceholderAPI.setPlaceholders(this, string)
    }
    CLib.get().audiences.sender(this).sendMessage(string.placeholders(*placeholder).colorize())
}

fun CommandSender.sendColorizedMessageFromMessagesFileList(path: String, vararg placeholder: Placeholder) {
    if (!CLib.get().messages) {
        logger().warning("You didn't setup messages in cLib main function! In order to use integrated messages.yml use it.")
        return
    }

    val section = CLib.get().messagesFile.getConfigurationSection(path)!!

    val list = section.getKeys(false)

    list.forEach {
        val string = section.getString("$it")

        this.sendColorizedMessageFromMessagesFile("$path.$it", *placeholder)
    }
}