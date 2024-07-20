package net.denistor.dungeonrpg.client;

import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.client.renders.entity.FoodTraderEntityRender;

import net.denistor.dungeonrpg.init.EntityRegister;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DungeonRPG_fabricClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_FOOD_TRADER_LAYER = new EntityModelLayer(new Identifier(DungeonRPG_fabric.MODID, "food_trader"), "main");
    //инициализация клиентской части
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegister.FOOD_TRADER, FoodTraderEntityRender::new);


    }
}
