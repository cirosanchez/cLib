package me.cirosanchez.clib

import jdk.nashorn.internal.runtime.regexp.joni.Config
import me.cirosanchez.clib.configuration.Configuration
import me.cirosanchez.clib.configuration.FileConfiguration
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CLib(javaPlugin: JavaPlugin) {


    val audiences = BukkitAudiences.create(javaPlugin)
    var messages: Boolean = false
    lateinit var messagesFile: Configuration

    init {
        plugin = javaPlugin
        instance = this
    }

    fun setupMessages() {
        if (messages) {
            messagesFile = FileConfiguration("messages.yml").loadConfig()
        }
    }


    companion object {
        lateinit var plugin: JavaPlugin
        var instance: CLib? = null

        fun get(): CLib {
            if (instance == null) {
                logger().severe("cLib hasn't been setup yet.")
                throw IllegalStateException("cLib hasn't been setup yet.")
            }

            return instance!!
        }
    }



}

fun logger() = Logger.getLogger("cLib")

fun cLib(plugin: JavaPlugin, init: CLib.() -> Unit = {}): CLib {
    return CLib(plugin).apply {
        init()
        setupMessages() // Call the setup method after init block
    }
}

fun getPlugin(): JavaPlugin {
    return CLib.Companion.plugin
}
