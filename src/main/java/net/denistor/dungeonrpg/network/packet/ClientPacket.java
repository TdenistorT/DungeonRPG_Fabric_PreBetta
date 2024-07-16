package net.denistor.dungeonrpg.network.packet;


import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ClientPacket {
    public final Identifier ID;
    public final ServerPlayNetworking.PlayChannelHandler HANDLER;

    public ClientPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(DungeonRPG_fabric.MODID, id);
        HANDLER = handler;
    }
}
