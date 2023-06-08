import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

group = "com.github.allinkdev"
version = "1.2-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven("https://repo.scissors.gg/repository/scissors-snapshot/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://nexus-01.core.atlas-media.co.uk/repository/totalfreedom-development/")
}

dependencies {
    compileOnly("me.totalfreedom.scissors:Scissors-API:1.19.4-R0.1-SNAPSHOT")
    compileOnly("net.essentialsx:EssentialsX:2.19.7")

    compileOnly("me.totalfreedom:TotalFreedomMod:2021.09")
}

bukkit {
    author = "videogamesm12"
    description = "Temporary plugin that patches a known TPToggle bypass exploit in Essentials"
    apiVersion = "1.17"
    name = "ESP"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "com.github.allinkdev.esp.Main"

    depend = listOf("Essentials", "TotalFreedomMod")
}