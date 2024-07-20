package net.denistor.dungeonrpg.client.renders.entity;

import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.entity.traders.FoodTraderEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FoodTraderEntityRender extends MobEntityRenderer<FoodTraderEntity, VillagerResemblingModel<FoodTraderEntity>> {
    // класс для рендерига модели и текстур торговца еды
    private static final Identifier TEXTURE = new Identifier(DungeonRPG_fabric.MODID, "textures/entity/food_trader.png");

    public FoodTraderEntityRender(EntityRendererFactory.Context context) {
        super(context, new VillagerResemblingModel<>(context.getPart(EntityModelLayers.VILLAGER)), 1F);

    }

    @Override
    public Identifier getTexture(FoodTraderEntity entity) {
        return TEXTURE;
    }
    @Override
    public void render(FoodTraderEntity mobEntity, float f, float g, MatrixStack matrixStack,
                   VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(1f, 1f, 1f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

}