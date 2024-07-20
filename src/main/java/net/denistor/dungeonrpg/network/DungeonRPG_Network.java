package net.denistor.dungeonrpg.network;


import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.Worlds.RegisterWorlds;
import net.denistor.dungeonrpg.network.packet.ClientPacket;

import net.denistor.dungeonrpg.network.packet.ServerPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class DungeonRPG_Network {// связь между клиентской части игры и серверной

    public static final ClientPacket teleport_to_TerraFortis = registerPacket("teleport_to_terrafortis", (server, player, handler, buf, responseSender) -> {
        ServerWorld terraFortis = server.getWorld(RegisterWorlds.TERRAFORTIS_LEVEL_KEY);

        player.teleport(terraFortis,0,100,0,1f,1f);

    });
    public static final ClientPacket teleport_to_MainWorld = registerPacket("teleport_to_mainworld", (server, player, handler, buf, responseSender) -> {
        ServerWorld terraFortis = server.getOverworld();

        player.teleport(terraFortis,0,100,0,1f,1f);

    });




    private static ClientPacket registerPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ClientPacket packet = new ClientPacket(id, handler);
        ServerPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }
    private static ServerPacket registerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler)  {
        ServerPacket packet = new ServerPacket(id, handler);
        ClientPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }

    //sends
    public static void send(ClientPacket packet) {
        ClientPlayNetworking.send(packet.ID, PacketByteBufs.create());
    }
    public static void send(ClientPacket packet, PacketByteBuf data) {
        ClientPlayNetworking.send(packet.ID, data);
    }
    public static void send(ServerPacket packet, ServerPlayerEntity receiver) {
        ServerPlayNetworking.send(receiver, packet.ID, PacketByteBufs.create());
    }
    public static void send(ServerPacket packet, ServerPlayerEntity receiver, PacketByteBuf data) {
        ServerPlayNetworking.send(receiver, packet.ID, data);
    }
    public static void register() {
        DungeonRPG_fabric.LOGGER.info("Registering network for: " + DungeonRPG_fabric.MODID);
    }
}
