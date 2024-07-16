package net.denistor.dungeonrpg.init;


import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.staffs.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RegisterItems {
    //Item_groups
    public static final RegistryKey<ItemGroup> currency = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(DungeonRPG_fabric.MODID, "currency_group"));
    public static final RegistryKey<ItemGroup> staffs = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(DungeonRPG_fabric.MODID, "staffs_group"));



    //currency
    public static final Item terracoin = registerItem("terracoin", new Item(new FabricItemSettings()));
    public static final Item blue_star = registerItem("blue_star", new Item(new FabricItemSettings()));
    public static final Item green_star = registerItem("green_star", new Item(new FabricItemSettings()));
    public static final Item red_star = registerItem("red_star", new Item(new FabricItemSettings()));
    public static final Item white_star = registerItem("white_star", new Item(new FabricItemSettings()));
    public static final Item yellow_star = registerItem("yellow_star", new Item(new FabricItemSettings()));
    //staffs
    public static final Item fire_staff = registerItem("fire_staff", new FireStaff(new FabricItemSettings().maxDamage(100)));
    public static final Item lightning_staff = registerItem("lightning_staff", new LightningStaff(new FabricItemSettings().maxDamage(100)));
    public static final Item explosive_staff = registerItem("explosive_staff", new ExplosiveStaff(new FabricItemSettings().maxDamage(100)));
    public static final Item teleport_staff = registerItem("teleport_staff", new TeleportStaff(new FabricItemSettings().maxDamage(100)));
    public static final Item fly_staff = registerItem("fly_staff", new FlyStaff(new FabricItemSettings().maxDamage(100)));




    private static Item registerItem(String id, Item item){return Registry.register(Registries.ITEM, new Identifier(DungeonRPG_fabric.MODID, id), item);}
//    private static Item registerItem(String id, Item item, RegistryKey<ItemGroup> registryKey ){
//        Item returnItem = Registry.register(Registries.ITEM, new Identifier(DungeonRPG_Main.MODID, id), item);
//        ItemGroupEvents.modifyEntriesEvent(registryKey).register(entries -> entries.add(returnItem));
//        return returnItem;
//    }

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, currency, FabricItemGroup.builder()
                .displayName(Text.translatable("currency"))
                .icon(() -> new ItemStack(RegisterItems.terracoin))
                .entries((context, entries) ->{
                    entries.add(new ItemStack(terracoin));
                    entries.add(new ItemStack(blue_star));
                    entries.add(new ItemStack(green_star));
                    entries.add(new ItemStack(red_star));
                    entries.add(new ItemStack(white_star));
                    entries.add(new ItemStack(yellow_star));
                })
                .build());
        Registry.register(Registries.ITEM_GROUP,staffs, FabricItemGroup.builder()
                .displayName(Text.translatable("Посохи"))
                .icon(() -> new ItemStack(RegisterItems.fire_staff))
                .entries((context, entries) ->{
                    entries.add(new ItemStack(fire_staff));
                    entries.add(new ItemStack(lightning_staff));
                    entries.add(new ItemStack(explosive_staff));
                    entries.add(new ItemStack(teleport_staff));
                    entries.add(new ItemStack(fly_staff));

                })
                .build());
        DungeonRPG_fabric.LOGGER.debug("register mod Items...");

    }
}
