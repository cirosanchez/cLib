package me.cirosanchez.clib.configuration

import me.cirosanchez.clib.CLib.Companion.plugin
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

abstract class Configuration() : YamlConfiguration() {
    private val file by lazy {  File(plugin.dataFolder, getFileName()) }

    companion object {
        var config: Configuration? = null
    }

    fun getConfig(): Configuration {
        if (config == null){
            config = this
        }
        return config!!
    }

    fun loadConfig() : Configuration {
        if (!file.exists()) plugin.saveResource(file.name, false)

        this.reload()
        return this
    }

    abstract fun getFileName(): String

    private fun reload() {
        super.load(file)
        super.save(file)
    }

    fun save(){
        super.save(file)
    }

}
