plugins {
    kotlin("jvm") version "2.0.21"
    id("com.gradleup.shadow") version "8.3.3"
    id("maven-publish")
    id("java")
}

group = "me.cirosanchez"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://jitpack.io")
    maven("https://repo.extendedclip.com/releases/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

    val lamp_version = "3.3.0"

    // https://mvnrepository.com/artifact/io.github.revxrsal/lamp.common
    implementation("io.github.revxrsal.Lamp:common:3.3.4")
    // https://mvnrepository.com/artifact/io.github.revxrsal/lamp.paper
    implementation("com.github.Revxrsal.Lamp:bukkit:3.3.4")



    implementation("org.mongodb:bson:4.3.4")
    implementation("org.mongodb:mongodb-driver-kotlin-sync:4.11.0")
    compileOnly("me.clip:placeholderapi:2.11.6")
    implementation(kotlin("reflect"))
}

val targetJavaVersion = 17
kotlin {
    jvmToolchain(targetJavaVersion)
}

val javaVersion = 17
val javaVersionEnumMember = JavaVersion.VERSION_17

java {
    sourceCompatibility = javaVersionEnumMember
    targetCompatibility = javaVersionEnumMember

    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}




tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.named("shadowJar").get()) {
                classifier = null
            }
            groupId = "me.cirosanchez"
            artifactId = "clib"
            version = "v0.1.5"

        }
    }
}



