package net.denistor.dungeonrpg.entity.traders;

import net.denistor.dungeonrpg.DungeonRPG_fabric;
import net.denistor.dungeonrpg.init.RegisterItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.ai.goal.StopAndLookAtEntityGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class FoodTraderEntity extends WanderingTraderEntity {//класс для обработки торговца еды
    private final int TRADE_REFRESH_INTERVAL = 1200; // 1200 тиков = 1 минута
    private int tickCounter;

    public FoodTraderEntity(EntityType<? extends WanderingTraderEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("tickCounter", tickCounter);

    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if(nbt.contains("tickCounter", NbtElement.INT_TYPE)){
            tickCounter = nbt.getInt("tickCounter");
        }
    }

    @Override
    protected void initGoals() {
         Set<PrioritizedGoal> goals =  this.goalSelector.getGoals();
         goals.clear();
        this.goalSelector.add(0, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0f, 1.0f));

    }



    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double distance) {
        double d = 64 * getRenderDistanceMultiplier();
        return distance < d * d;
    }



    @Override
    public boolean damage(DamageSource source, float amount) {

        return false;
    }



    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.setCustomName(Text.translatable("Продавец еды"));
        TradeOfferList offers = this.getOffers();
        offers.clear();
        ItemStack whiteStar = new ItemStack(RegisterItems.white_star);
        ItemStack blueStarr = new ItemStack(RegisterItems.blue_star);
        ItemStack greenStar = new ItemStack(RegisterItems.green_star);
        ItemStack redStar = new ItemStack(RegisterItems.red_star);
        ItemStack YellowStarr = new ItemStack(RegisterItems.yellow_star);

        // Добавьте ваши кастомные торговые предложения
        reloadTrades();
    }




    @Override
    public void mobTick() {
        tickCounter ++;
        if ( tickCounter>= TRADE_REFRESH_INTERVAL) {
            tickCounter = 0;
            reloadTrades();
        }
    }





    private void reloadTrades(){

        DungeonRPG_fabric.LOGGER.info("updateTrades");
        List<TradeOffer> group1 = new ArrayList<>();
        List<TradeOffer> group2 = new ArrayList<>();
        List<TradeOffer> group3 = new ArrayList<>();
        List<TradeOffer> group4 = new ArrayList<>();
        List<TradeOffer> group5 = new ArrayList<>();



        TradeOfferList offers = this.getOffers();
        offers.clear();
        group1.add(new TradeOffer(new ItemStack(RegisterItems.green_star, 2), new ItemStack(Items.APPLE, 8), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(Items.APPLE, 16), new ItemStack(RegisterItems.green_star, 2), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(RegisterItems.blue_star, 2), new ItemStack(Items.MELON_SLICE, 8), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(Items.MELON_SLICE, 16), new ItemStack(RegisterItems.blue_star, 2), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(RegisterItems.blue_star, 3), new ItemStack(Items.SWEET_BERRIES, 8), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(Items.SWEET_BERRIES, 16), new ItemStack(RegisterItems.blue_star, 3), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(RegisterItems.green_star, 3), new ItemStack(Items.GLOW_BERRIES, 8), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(Items.GLOW_BERRIES, 16), new ItemStack(RegisterItems.green_star, 3), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(RegisterItems.green_star, 2), new ItemStack(RegisterItems.blue_star), new ItemStack(Items.CHORUS_FRUIT, 8), 100, 5, 0.05F));
        group1.add(new TradeOffer(new ItemStack(Items.CHORUS_FRUIT, 8), new ItemStack(RegisterItems.white_star, 12), 100, 5, 0.05F));


        group2.add(new TradeOffer(new ItemStack(Items.WHEAT, 32), new ItemStack(RegisterItems.blue_star, 5), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(RegisterItems.green_star), new ItemStack(Items.CARROT, 8), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(Items.CARROT, 16), new ItemStack(RegisterItems.green_star), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(RegisterItems.green_star, 2), new ItemStack(Items.BAKED_POTATO, 8), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(Items.POTATO, 16), new ItemStack(RegisterItems.white_star, 8), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(Items.BEETROOT, 16), new ItemStack(RegisterItems.blue_star, 3), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(Items.PUMPKIN, 6), new ItemStack(RegisterItems.blue_star, 3), 100, 5, 0.05F));
        group2.add(new TradeOffer(new ItemStack(RegisterItems.green_star, 4), new ItemStack(Items.BREAD, 8), 100, 5, 0.05F));


        group3.add(new TradeOffer(new ItemStack(Items.BEEF, 16), new ItemStack(RegisterItems.green_star, 8), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.RABBIT, 16), new ItemStack(RegisterItems.green_star, 12), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.PORKCHOP, 16), new ItemStack(RegisterItems.green_star, 6), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.CHICKEN, 16), new ItemStack(RegisterItems.red_star), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.COD, 16), new ItemStack(RegisterItems.green_star, 4), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.SALMON, 16), new ItemStack(RegisterItems.green_star, 12), 100, 5, 0.05F));
        group3.add(new TradeOffer(new ItemStack(Items.MUTTON, 16), new ItemStack(RegisterItems.green_star, 7), 100, 5, 0.05F));


        group4.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star), new ItemStack(RegisterItems.red_star), new ItemStack(Items.RABBIT_STEW, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star), new ItemStack(Items.COOKED_BEEF, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star, 3), new ItemStack(Items.COOKED_PORKCHOP, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star), new ItemStack(Items.PUMPKIN_PIE, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star, 2), new ItemStack(Items.MUSHROOM_STEW, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(Items.BEETROOT_SOUP, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star, 4), new ItemStack(Items.COOKED_MUTTON, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star, 2), new ItemStack(Items.COOKED_CHICKEN, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star, 3), new ItemStack(Items.COOKED_SALMON, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star), new ItemStack(Items.HONEY_BOTTLE, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star), new ItemStack(RegisterItems.green_star, 3), new ItemStack(Items.COOKED_RABBIT, 8), 100, 5, 0.05F));
        group4.add(new TradeOffer(new ItemStack(RegisterItems.red_star), new ItemStack(RegisterItems.green_star, 4), new ItemStack(Items.COOKED_COD, 8), 100, 5, 0.05F));


        group5.add(new TradeOffer(new ItemStack(RegisterItems.red_star, 3), new ItemStack(RegisterItems.green_star, 2), new ItemStack(Items.GOLDEN_CARROT), 100, 5, 0.05F));
        group5.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star), new ItemStack(RegisterItems.red_star), new ItemStack(Items.GOLDEN_APPLE), 100, 5, 0.05F));
        group5.add(new TradeOffer(new ItemStack(RegisterItems.yellow_star, 7), new ItemStack(RegisterItems.red_star), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 100, 5, 0.05F));

        int a1 = (int) (Math.random() * group1.size());
        int a2 = (int) (Math.random() * group2.size());
        int a3 = (int) (Math.random() * group3.size());
        int a4 = (int) (Math.random() * group4.size());
        int a5 = (int) (Math.random() * group5.size());

        int b1 = (int) (Math.random() * group1.size());
        int b2 = (int) (Math.random() * group2.size());
        int b3 = (int) (Math.random() * group3.size());
        int b4 = (int) (Math.random() * group4.size());
        int b5 = (int) (Math.random() * group5.size());

        while(b1 == a1){b1 =(int) (Math.random() * group1.size());}
        while(b2 == a2){b2 =(int) (Math.random() * group2.size());}
        while(b3 == a3){b3 =(int) (Math.random() * group3.size());}
        while(b4 == a4){b4 =(int) (Math.random() * group4.size());}
        while(b5 == a5){b5 =(int) (Math.random() * group5.size());}

        offers.add(group1.get(a1));
        offers.add(group1.get(b1));

        offers.add(group2.get(a2));
        offers.add(group2.get(b2));

        offers.add(group3.get(a3));
        offers.add(group3.get(b3));

        offers.add(group4.get(a4));
        offers.add(group4.get(b4));

        offers.add(group5.get(a5));
        offers.add(group5.get(b5));

    }


}