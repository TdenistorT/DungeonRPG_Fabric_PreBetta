package net.denistor.dungeonrpg.staffs;

import net.denistor.dungeonrpg.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningStaff extends Item {// посох молнии
    public LightningStaff(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!user.getWorld().isClient && user != null) {

            BlockPos targetPos = Utils.getTargetBlock(world,user,100);
            if (targetPos != null) {
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world); // Create the lightning bolt
                lightning.setPos(targetPos.getX(), targetPos.getY(), targetPos.getZ());
                world.spawnEntity(lightning);
                itemStack.damage(1,user,e->e.sendToolBreakStatus(hand));
                user.getItemCooldownManager().set(itemStack.getItem(),100);
                if (world instanceof ServerWorld) {
                    ((ServerWorld) world).playSound(null, user.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 1.0F, 1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, user.getBlockPos().getX(), user.getBlockPos().getY() +1, user.getBlockPos().getZ(), 100, 1.0, 1.0, 1.0, 0.0);
                }
            }

        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }


}
