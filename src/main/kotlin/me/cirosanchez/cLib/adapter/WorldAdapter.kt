package me.cirosanchez.cLib.adapter

import com.google.gson.*
import org.bukkit.Bukkit
import org.bukkit.World
import java.lang.reflect.Type
import java.util.*

object WorldAdapter : JsonDeserializer<World>, JsonSerializer<World> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): World {
        if (json == null) throw JsonParseException("JSON can't be null.")
        if (json !is JsonObject) throw JsonParseException("JSON must be an object.")

        val uid = json.get("uid").asString
        val uuid = UUID.fromString(uid)

        val world = Bukkit.getWorld(uuid) ?: throw JsonParseException("World with UUID $uid not found.")

        return world
    }

    override fun serialize(src: World?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if (src == null) throw JsonParseException("World can't be null.")

        val obj = JsonObject()
        obj.addProperty("uid", src.uid.toString())

        return obj
    }

}