package net.denistor.dungeonrpg.staffs;

import net.denistor.dungeonrpg.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExplosiveStaff extends Item {// взрывной посох
    public ExplosiveStaff(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!user.getWorld().isClient && user != null) {

            BlockPos targetPos = Utils.getTargetBlock(world,user,100);
            if (targetPos != null) {
                world.createExplosion(user, targetPos.getX(), targetPos.getY(), targetPos.getZ(), 5.0F, World.ExplosionSourceType.MOB);
                itemStack.damage(1,user,e->e.sendToolBreakStatus(hand));
                user.getItemCooldownManager().set(itemStack.getItem(),100);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 1.0F, 1.0F);

            }

        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}
