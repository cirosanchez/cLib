package me.cirosanchez.clib.adapter

import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import kotlin.reflect.KClass

interface Adapter<T : Any> : JsonSerializer<T>, JsonDeserializer<T> {
    val type: KClass<T>

    fun getClass(): Class<T> {
        return type.java
    }
}