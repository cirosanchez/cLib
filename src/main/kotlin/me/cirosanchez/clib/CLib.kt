package me.cirosanchez.clib

import me.cirosanchez.clib.configuration.Configuration
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CLib(javaPlugin: JavaPlugin) {


    val audiences = BukkitAudiences.create(javaPlugin)
    lateinit var messagesFile: Configuration

    init {
        plugin = javaPlugin
        instance = this
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


    fun messagesFile(configuration: Configuration){
        this.messagesFile = configuration
    }
}

fun logger() = Logger.getLogger("cLib")

fun cLib(plugin: JavaPlugin, init: CLib.() -> Unit = {}): CLib = CLib(plugin).apply(init)

