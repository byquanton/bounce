import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    id("xyz.jpenilla.run-paper") version "1.0.6"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "me.byquanton.plugins"
version = "1.0.0"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
}

bukkit {
    main = "me.byquanton.plugins.bounce.BouncePlugin"
    apiVersion = "1.19"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    authors = listOf("byquanton")
    commands {
        register("bounce")
    }
    permissions{
        register("bounce.use") {
            description = "Allows the player to use the /bounce command"
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("bounce.bypass") {
            description = "Prevents the player from being bounced"
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    runServer{
        minecraftVersion.set("1.19.2")
    }
}