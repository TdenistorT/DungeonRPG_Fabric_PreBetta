package net.denistor.dungeonrpg.init.World;


import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class RegisterWorlds {

        public static final RegistryKey<DimensionOptions> TERRAFORTIS_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
                new Identifier(DungeonRPG_fabric.MODID, "terrafortis"));
        public static final RegistryKey<World> TERRAFORTIS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
                new Identifier(DungeonRPG_fabric.MODID, "terrafortis"));
        public static final RegistryKey<DimensionType> TERRAFORTIS_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
                new Identifier(DungeonRPG_fabric.MODID, "terrafortis_type"));

        public static void bootstrapType(Registerable<DimensionType> context) {
            context.register(TERRAFORTIS_DIM_TYPE, new DimensionType(
                    OptionalLong.of(12000), // fixedTime
                    false, // hasSkylight
                    false, // hasCeiling
                    false, // ultraWarm
                    true, // natural
                    1.0, // coordinateScale
                    true, // bedWorks
                    false, // respawnAnchorWorks
                    0, // minY
                    256, // height
                    256, // logicalHeight
                    BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                    DimensionTypes.OVERWORLD_ID, // effectsLocation
                    1.0f, // ambientLight
                    new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        }


}
