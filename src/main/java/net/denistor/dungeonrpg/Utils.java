package net.denistor.dungeonrpg;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class Utils {
    public static BlockPos getTargetBlock(World world, PlayerEntity player, double maxDistance) {
        // Начальная позиция трассировки

        Vec3d playerPos = player.getPos();
        // Вектор взгляда
        Vec3d viewVector = player.getRotationVec(1.0F);
        Vec3d currentPos = new Vec3d(playerPos.x,playerPos.y +1,playerPos.z);

        // Нормализуем вектор взгляда
        Vec3d normalizedViewVector = viewVector.normalize();

        // Длина одного шага (меньше блока, можно подстроить как надо)
        double step = 0.1;

        // Пройдём вдоль вектора взгляда
        for (double d = 0; d <= maxDistance; d += step) {
            // Посчитаем текущую позицию
            currentPos = currentPos.add(normalizedViewVector.multiply(step));

            // Преобразуем текущую позицию в целочисленный BlockPos
            Vec3i vec3i = new Vec3i((int)currentPos.x,(int)currentPos.y,(int)currentPos.z);
            BlockPos blockPos = new BlockPos(vec3i);

            // Если текущий блок не является пустым (то есть, это не воздух), мы нашли блок на пути взгляда
            if (!world.isAir(blockPos)) {
                return blockPos;
            }
        }

        // Если не нашли блок, вернем null
        return null;
    }
}
