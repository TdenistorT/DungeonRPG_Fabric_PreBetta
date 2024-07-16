package net.denistor.dungeonrpg;

import net.denistor.dungeonrpg.init.RegisterItems;
import net.denistor.dungeonrpg.init.RegisterTraders;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonRPG_fabric implements ModInitializer {
    public static final String MODID = "dungeonrpg_fabric";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        RegisterItems.register();
        HotKeys.register();
        RegisterTraders.registry();
    }
}
