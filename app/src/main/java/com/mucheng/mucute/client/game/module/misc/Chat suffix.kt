package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.module.Module
import com.mucheng.mucute.client.game.module.Category
import com.mucheng.mucute.client.network.PacketContext
import protocol.packet.TextPacket

class ChatSuffix : Module("ChatSuffix", "Appends Invis Client brand suffix to chats", Category.MISC) {
    private val suffix = " | InvisClient Beta"

    override fun onPacketSend(ctx: PacketContext) {
        val packet = ctx.packet
        
        if (packet is TextPacket) {
            if (packet.type == TextPacket.Type.CHAT) {
                val originalMessage = packet.message
                
                if (!originalMessage.startsWith("/") && !originalMessage.startsWith(".")) {
                    packet.message = originalMessage + suffix
                }
            }
        }
    }
}

