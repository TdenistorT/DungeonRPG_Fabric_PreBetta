package net.denistor.dungeonrpg.staffs;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireStaff extends Item {
    public FireStaff(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        FireballEntity fireballEntity;
        if(!user.getWorld().isClient && user != null){
            ItemStack itemStack = user.getStackInHand(hand);
            Vec3d rotationVec = user.getRotationVec(1.0F);
            double x =  rotationVec.x ;
            double y =  rotationVec.y ;
            double z =  rotationVec.z ;

            fireballEntity = new FireballEntity(world,user,x,y,z,1);
            fireballEntity.setPosition(fireballEntity.getX(),fireballEntity.getY()+1,fireballEntity.getZ());
            fireballEntity.setOwner(user);
            world.spawnEntity(fireballEntity);
            itemStack.damage(1,user,e->e.sendToolBreakStatus(hand));
            user.getItemCooldownManager().set(itemStack.getItem(),100);
            world.playSound(null,user.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,SoundCategory.PLAYERS,1,1);
            ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, user.getBlockPos().getX(), user.getBlockPos().getY() +1, user.getBlockPos().getZ(), 100, 1.0, 1.0, 1.0, 0.0);


        }
        if (world.isClient && user != null) {

            Vec3d rotationVec = user.getRotationVec(1.0F);
            double x =  rotationVec.x ;
            double y =  rotationVec.y ;
            double z =  rotationVec.z ;
            for (int i = 0; i < 100; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
                double offsetY = (world.random.nextDouble() - 0.5) * 0.5;
                double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;
                world.addParticle(ParticleTypes.FLAME, user.getX(), user.getY() + 1.0, user.getZ(), x +offsetX, y+offsetY, z+offsetZ);
            }

        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        if(!user.getWorld().isClient && user != null){
            entity.setOnFireFor(15);
            world.playSound(null,user.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,SoundCategory.PLAYERS,1,1);
        }
        if (world.isClient && user != null) {

            Vec3d rotationVec = user.getRotationVec(1.0F);
            double x =  rotationVec.x ;
            double y =  rotationVec.y ;
            double z =  rotationVec.z ;
            for (int i = 0; i < 100; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
                double offsetY = (world.random.nextDouble() - 0.5) * 0.5;
                double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;
                world.addParticle(ParticleTypes.FLAME, user.getX(), user.getY() + 1.0, user.getZ(), x +offsetX, y+offsetY, z+offsetZ);
            }

        }
        return ActionResult.PASS;
    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}
