package moe.xmcn.spcraft.todaysign

import moe.xmcn.spcraft.todaysign.listener.OnGroupMessageEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class TodaySign : JavaPlugin() {

    override fun onLoad() {
        logger.log(Level.INFO, "签到组件加载中")
    }

    override fun onEnable() {
        logger.log(Level.INFO, "签到组件启动中")
        server.pluginManager.registerEvents(OnGroupMessageEvent(), this)
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

}