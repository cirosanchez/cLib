package me.cirosanchez.clib.cooldown

import java.time.Duration
import java.time.Instant
import java.util.*


class Cooldown {
    private val map: MutableMap<UUID, Instant> = HashMap()

    // Set cooldown
    fun setCooldown(key: UUID, duration: Duration?) {
        map[key] = Instant.now().plus(duration)
    }

    // Check if cooldown has expired
    fun hasCooldown(key: UUID): Boolean {
        val cooldown = map[key]
        return cooldown != null && Instant.now().isBefore(cooldown)
    }

    // Remove cooldown
    fun removeCooldown(key: UUID): Instant? {
        return map.remove(key)
    }

    // Get remaining cooldown time
    fun getRemainingCooldown(key: UUID): Duration {
        val cooldown = map[key]
        val now = Instant.now()
        return if (cooldown != null && now.isBefore(cooldown)) {
            Duration.between(now, cooldown)
        } else {
            Duration.ZERO
        }
    }
}