package net.denistor.dungeonrpg.network.packet;

import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ServerPacket {
    public final Identifier ID;
    public final ClientPlayNetworking.PlayChannelHandler HANDLER;

    public ServerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(DungeonRPG_fabric.MODID, id);
        HANDLER = handler;
    }
}
