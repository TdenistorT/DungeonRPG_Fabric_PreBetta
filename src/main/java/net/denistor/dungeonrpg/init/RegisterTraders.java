package net.denistor.dungeonrpg.init;

import com.google.common.collect.ImmutableSet;
import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.BarrierBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;

import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;



public class RegisterTraders {
    public static final PointOfInterestType food_trader_npc = registerPointOfInterest("food_trader_npc", Blocks.BARRIER);
    public static final VillagerProfession food_trade_profession = registerProfession("food_trade_profession",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(),
                    new Identifier(DungeonRPG_fabric.MODID,"food_trader_npc")));

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type){
        return Registry.register(Registries.VILLAGER_PROFESSION, new Identifier(DungeonRPG_fabric.MODID,name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_YES));


    }
    private static void registerTrades(){
        TradeOfferHelper.registerVillagerOffers(food_trade_profession, 1, factories ->{
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(RegisterItems.blue_star),
                    new ItemStack(RegisterItems.white_star, 5),
                    10,10,0.05f
            )));});
    }



    private static PointOfInterestType registerPointOfInterest(String id, Block block){
        return PointOfInterestHelper.register(
                new Identifier(DungeonRPG_fabric.MODID, id),
                1,
                1,
                block
        );
    }
    public  static void registry(){
        registerTrades();
    }
}
