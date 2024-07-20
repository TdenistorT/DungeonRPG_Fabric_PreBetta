package net.denistor.dungeonrpg.init;

import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.entity.traders.FoodTraderEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegister implements ModInitializer {//регистрация сущностей


    public static final EntityType<FoodTraderEntity> FOOD_TRADER= Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DungeonRPG_fabric.MODID, "food_trader"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FoodTraderEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    @Override
    public void onInitialize() {

    }
    public static DefaultAttributeContainer.Builder createCustomTradersAttributes() {
        return VillagerEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1000);
    }
}
