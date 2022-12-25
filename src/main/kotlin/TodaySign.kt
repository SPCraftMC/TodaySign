package moe.xmcn.spcraft.todaysign

import moe.xmcn.spcraft.todaysign.listener.OnGroupMessageEvent
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.logging.Level

class TodaySign : JavaPlugin(), CommandExecutor {

    override fun onLoad() {
        logger.log(Level.INFO, "签到组件加载中")
    }

    override fun onEnable() {
        logger.log(Level.INFO, "签到组件启动中")
        saveDefaultConfig()
        server.pluginManager.registerEvents(OnGroupMessageEvent(), this)
        server.getPluginCommand("todaysign")?.setExecutor(this)
        server.scheduler.scheduleSyncRepeatingTask(
            this,
            DataResetor(),
            0L,
            1000L
        )
    }

    override fun onDisable() {
        logger.log(Level.INFO, "签到组件卸载中")
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String?>): Boolean {
        if (args.size == 1) {
            if (args[0] == "reset" && sender.isOp) {
                config.set("signed", ArrayList<String>())
                config.save(File(dataFolder, "config.yml"))
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a签到数据已重置"))
            } else if (args[0] == "reload" && sender.isOp) {
                reloadConfig()
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a配置已重载"))
            }
        }
        return true
    }

}