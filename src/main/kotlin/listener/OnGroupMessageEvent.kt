package moe.xmcn.spcraft.todaysign.listener

import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.api.MiraiMC
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import me.yic.xconomy.api.XConomyAPI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.io.File
import java.math.BigDecimal
import kotlin.collections.ArrayList

class OnGroupMessageEvent : Listener {

    private val xcapi = XConomyAPI()
    private val plugin = Bukkit.getPluginManager().getPlugin("TodaySign")

    private var list = ArrayList<String>()

    @EventHandler
    fun onGroupMessage(e : MiraiGroupMessageEvent) {

        list = plugin?.config?.getStringList("signed") as ArrayList<String>

        if (
            e.botID == plugin.config.getLong("bot")
            && e.groupID == plugin.config.getLong("group")
        ) {
            val playerId = e.senderID
            val playerUuid = MiraiMC.getBind(playerId)
            if (e.message == "!spm 签到") {
                if (!plugin.config.getStringList("signed").contains(playerUuid?.let { Bukkit.getOfflinePlayer(it).name })) {
                    if (playerUuid != null) {

                        val addLimit = BigDecimal.valueOf(plugin.config.getLong("sign.money"))
                        xcapi.changePlayerBalance(
                            playerUuid,
                            playerUuid.let { Bukkit.getOfflinePlayer(it).name }, addLimit, true
                        )

                        playerUuid.let { Bukkit.getOfflinePlayer(it).name }?.let { list.add(it) }

                        plugin.config.set("signed", list)
                        plugin.config.save(File(plugin.dataFolder, "config.yml"))

                        MiraiBot.getBot(plugin.config.getLong("bot")).getGroup(plugin.config.getLong("group"))
                            .sendMessageMirai(
                                "签到成功，奖励 " +
                                        plugin.config.getLong("sign.money") +
                                        " 硬币。"
                            )

                    } else {
                        MiraiBot.getBot(plugin.config.getLong("bot")).getGroup(plugin.config.getLong("group")).sendMessageMirai("没有查询到玩家数据，请先绑定QQ！")
                    }
                } else {
                    MiraiBot.getBot(plugin.config.getLong("bot")).getGroup(plugin.config.getLong("group")).sendMessageMirai("你今天已经签到过了！不能贪心！")
                }
            }
        }
    }

}