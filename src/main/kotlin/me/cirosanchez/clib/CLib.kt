package me.cirosanchez.clib

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.cirosanchez.clib.adapter.WorldAdapter
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class CLib(javaPlugin: JavaPlugin,
           var gson: Gson = GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapter(World::class.java,
               WorldAdapter).create(),
    ) {

    init {
        plugin = javaPlugin
        started = true
    }
    companion object {
        var started = false

        lateinit var plugin: JavaPlugin
        val audiences = BukkitAudiences.create(plugin)
    }
}

fun clib(plugin: JavaPlugin): CLib = CLib(plugin)
