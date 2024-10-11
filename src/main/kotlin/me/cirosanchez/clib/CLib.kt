package me.cirosanchez.clib

import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

class CLib(javaPlugin: JavaPlugin) {


    val audiences = BukkitAudiences.create(javaPlugin)

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
}

fun logger() = Logger.getLogger("cLib")

fun cLib(plugin: JavaPlugin, init: CLib.() -> Unit = {}): CLib = CLib(plugin).apply(init)

