package net.denistor.dungeonrpg;

import net.denistor.dungeonrpg.init.BindKeys;
import net.denistor.dungeonrpg.init.EntityRegister;
import net.denistor.dungeonrpg.init.RegisterItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.denistor.dungeonrpg.init.EntityRegister.createCustomTradersAttributes;

public class DungeonRPG_fabric implements ModInitializer {
    public static final String MODID = "dungeonrpg_fabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    //инициализация ресурсов мода на серверной части
    @Override
    public void onInitialize() {
        RegisterItems.register();
        BindKeys.register();

        FabricDefaultAttributeRegistry.register(EntityRegister.FOOD_TRADER, createCustomTradersAttributes());

    }
}
