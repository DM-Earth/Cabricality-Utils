package com.dm.earth.cabricality.content.alchemist.laser;

import com.dm.earth.cabricality.util.PositionUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class LaserBehaviours {
	public static void attackNearby(World world, BlockPos pos, float power) {
		float len = power * 3F;
		Box box = Box.of(PositionUtil.fromBlockPos(pos), len, len, len);
		List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, box, Entity::isLiving);
		for (LivingEntity entity : entities) entity.damage(DamageSource.MAGIC, len);
	}
}
