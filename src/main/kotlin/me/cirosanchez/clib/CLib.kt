package me.cirosanchez.clib

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.cirosanchez.clib.adapter.Adapter
import me.cirosanchez.clib.configuration.Configuration
import me.cirosanchez.clib.configuration.FileConfiguration
import me.cirosanchez.clib.storage.MongoDB
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger
import kotlin.jvm.java

class CLib(javaPlugin: JavaPlugin) {


    val audiences = BukkitAudiences.create(javaPlugin)
    var messages: Boolean = false
    var mongoURI: String = ""
    var mongoDB: String = ""
    var adapters = listOf(Adapter::class.java)
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

    fun setupMongo(){
        MongoDB.mongo(MongoDB.Settings())
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


fun getGson(): Gson {
    val builder = GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()

    CLib.get().adapters.forEach { adapter ->
        builder.registerTypeHierarchyAdapter(adapter.componentType, adapter)
    }

    return builder.create()
}

fun Any.toJson(): String {
    return getGson().toJson(this)
}