package me.cirosanchez.clib.adapter

import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer

interface Adapter<T> : JsonSerializer<T>, JsonDeserializer<T> {
}