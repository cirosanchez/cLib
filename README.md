# cLib


<div style="display: flex; justify-content: center;">
    <img style="padding-right:11px" src="https://jitpack.io/v/cirosanchez/cLib.svg" alt="JitPack">
    <img alt="Discord" src="https://img.shields.io/discord/1209154009420795946">
</div>

cLib is a personal library used to avoid having to copy and paste an insane amount of dependencies, files and other resources to the projects I do.

The objective is to provide a variety of my own resources and other resources from other good libraries.

## Adding as a Dependency
Remember to replace {VERSION} with the version from the Jitpack repo displayed in the top of the readme file.

### Maven
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
	    <groupId>com.github.cirosanchez</groupId>
	    <artifactId>cLib</artifactId>
	    <version>{VERSION}</version>
	</dependency>
```

### Gradle 
```kts
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.github.cirosanchez:cLib:{VERSION}")
}
```

## List of Utilities Available
1. Extension Functions
2. Cooldown
3. Placeholder Utility

### Extension Functions
At the moment a little basic extension functions are available. The most importants one can be found at [Code](https://github.com/cirosanchez/cLib/tree/master/src/main/kotlin/me/cirosanchez/cLib/extension).

### Cooldown
Cooldown class provides a cooldown manager for an specific function.

An example using [Lamp Command Framework](https://github.com/Revxrsal/Lamp) for a simple message command with Cooldown implemented.
```kotlin
@Command("msg","message","pm")
@CommandPermission("survival.command.message")
@Description("Send a private message to another player.")
class MessageCommand {

    val cooldown = Cooldown()

    @DefaultFor("~")
    fun default(actor: Player, target: Player, string: String){

        val uuid = actor.uniqueId
        
        if (cooldown.hasCooldown(uuid)){
            val secs = cooldown.getRemainingCooldown(uuid)
            actor.sendMessage("<red>You're in cooldown! Please wait <yellow>{$secs}s</yellow></red>")
            return
        }

        cooldown.setCooldown(actor.uniqueId, Duration.ofSeconds(3))

        target.sendMessage("From ${actor.name}: "+string.colorize())
    }

}
```


### Placeholder Utility
This placeholder utility makes thirty times easier to implement native placeholders. 

```kotlin
  val string = "Hey i am %name%. I am a %placeholder%"
  val placeholder = Placeholder("%placeholder%", "dog")
  val otherPlaceholder = Placeholder("%name%", "John Doe")

  val modifiedString = string.placeholders(placeholder, otherPlaceholder)
  // Result = "Hey i am John Doe. I am a dog"


  // Player Usage
  player.sendMessage(modifiedString.placeholders(placeholder, otherPlaceholder).colorize()) // To be improved in next release.
```


Thanks for using this library, I'll try to update it regularly with new technologies I found! I am in my senior year and I don't have a lot of spare time, but for sure this won't be abandoned entirely.


Made with 💗 by Ciro.
