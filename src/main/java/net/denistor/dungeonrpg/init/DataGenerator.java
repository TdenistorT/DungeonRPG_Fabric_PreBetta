package net.denistor.dungeonrpg.init;

import net.denistor.dungeonrpg.Worlds.RegisterWorlds;
import net.denistor.dungeonrpg.Worlds.WorldGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class DataGenerator implements DataGeneratorEntrypoint {// инициализация разной даты в т.ч. миров
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(WorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, RegisterWorlds::bootstrapType);
    }
}
