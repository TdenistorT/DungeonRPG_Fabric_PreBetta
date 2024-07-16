package net.denistor.dungeonrpg.staffs;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlyStaff extends Item {
    public FlyStaff(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            if(user.isFallFlying()) {
                Vec3d rotationVec = user.getRotationVec(1.0F);
                double x = rotationVec.x;
                double y = rotationVec.y;
                double z = rotationVec.z;

                FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, new ItemStack(FireworkRocketItem.byRawId(1)), user);
                fireworkRocketEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
                world.spawnEntity(fireworkRocketEntity);

                itemStack.damage(1, user, e -> e.sendToolBreakStatus(hand));
                user.getItemCooldownManager().set(itemStack.getItem(), 100);
            }
            else{
                user.sendMessage(Text.translatable( "Посох полёта можно использовать только с рассправлеными крыльями").withColor(Colors.RED),true);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }
}
