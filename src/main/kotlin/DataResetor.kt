package moe.xmcn.spcraft.todaysign

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DataResetor : Runnable {

    fun plugin() : Plugin {
        return TodaySign()
    }
    fun config() : FileConfiguration {
        return TodaySign().config
    }

    override fun run() {
        val sdf = SimpleDateFormat("HH")
        val currentDate = sdf.format(Date())
        if (currentDate == "00") {
            config().set("signed", "")
            config().save(File(plugin().dataFolder, "config.yml"))
        }
    }

}