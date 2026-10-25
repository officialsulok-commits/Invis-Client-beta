package com.mucheng.mucute.client.game.module.combat

import com.mucheng.mucute.client.game.module.Module
import com.mucheng.mucute.client.game.module.Category
import com.mucheng.mucute.client.network.PacketContext
import protocol.packet.MobEquipmentPacket
import protocol.types.ItemData

class AutoTotem : Module("AutoTotem", "Equips a totem when offhand is empty", Category.COMBAT) {

    override fun onPacketReceive(ctx: PacketContext) {
        val player = ctx.playerSession ?: return
        
        if (player.offhandItem.id != "minecraft:totem_of_undying") {
            val totemSlot = player.inventory.findItemSlot("minecraft:totem_of_undying")
            
            if (totemSlot != -1) {
                val equipPacket = MobEquipmentPacket().apply {
                    runtimeEntityId = player.runtimeId
                    item = ItemData("minecraft:totem_of_undying", 0, 1)
                    slot = 0 
                    selectedSlot = 0
                    containerId = 119 
                }
                ctx.sendPacketToServer(equipPacket)
            }
        }
    }
}

