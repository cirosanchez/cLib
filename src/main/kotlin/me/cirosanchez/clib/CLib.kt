package me.cirosanchez.clib

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.cirosanchez.clib.adapter.DurationTypeAdapter
import me.cirosanchez.clib.adapter.LocationAdapter
import me.cirosanchez.clib.configuration.Configuration
import me.cirosanchez.clib.configuration.FileConfiguration
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration
import java.util.logging.Logger
import kotlin.jvm.java

class CLib(javaPlugin: JavaPlugin) {


    val audiences = BukkitAudiences.create(javaPlugin)
    var messages: Boolean = false
    var mongoURI: String = ""
    var mongoDB: String = ""
    lateinit var gson: Gson
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

val GSON = GsonBuilder()
    .setPrettyPrinting()
    .serializeNulls()
    .registerTypeHierarchyAdapter(Duration::class.java, DurationTypeAdapter())
    .registerTypeHierarchyAdapter(Location::class.java, LocationAdapter)
    .create()

fun Any.toJson(): String {
    return GSON.toJson(this)
}