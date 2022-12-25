package moe.xmcn.spcraft.todaysign

import org.bukkit.Bukkit
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DataResetor : Runnable {

    private val plugin = Bukkit.getPluginManager().getPlugin("TodaySign")

    private val config = plugin?.config

    override fun run() {
        val sdf = SimpleDateFormat("HH")
        val currentDate = sdf.format(Date())
        if (currentDate == "00") {
            config?.set("signed", ArrayList<String>())
            config?.save(File(plugin?.dataFolder, "config.yml"))
        }
    }

}