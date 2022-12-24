package moe.xmcn.spcraft.todaysign.listener

import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.api.MiraiMC
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import me.yic.xconomy.api.XConomyAPI
import moe.xmcn.spcraft.todaysign.TodaySign
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.math.BigDecimal

class OnGroupMessageEvent : Listener {

    private val plugin : Plugin = TodaySign()
    private val xcapi : XConomyAPI = XConomyAPI()

    @EventHandler
    fun onGroupMessage(e : MiraiGroupMessageEvent) {
        if (
            e.botID == plugin.config.getLong("bot")
            && e.groupID == plugin.config.getLong("group")
            ) {
            val playerId = e.senderID
            val playerUuid = MiraiMC.getBind(playerId)
            if (e.message == "!spm 签到") {
                if (plugin.config.getStringList("signed").contains(playerUuid?.let { Bukkit.getOfflinePlayer(it).name })) {

                    val addLimit = BigDecimal.valueOf(plugin.config.getLong("sign.money"))
                    xcapi.changePlayerBalance(
                        playerUuid,
                        playerUuid?.let { Bukkit.getOfflinePlayer(it).name }, addLimit, true
                    )
                    plugin.config.set("signed", playerUuid?.let { Bukkit.getOfflinePlayer(it).name })
                    plugin.config.save(File(plugin.dataFolder, "config.yml"))
                } else {
                    MiraiBot.getBot(plugin.config.getLong("bot")).getGroup(plugin.config.getLong("group")).sendMessageMirai("你今天已经签到过了！不能贪心！")
                }
            }
        }
    }

}