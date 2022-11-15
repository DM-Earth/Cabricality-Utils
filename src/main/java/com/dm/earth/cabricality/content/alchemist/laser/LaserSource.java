package com.dm.earth.cabricality.content.alchemist.laser;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.CabfDebugger;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import net.darktree.led.block.DirectionalDiodeLampBlock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class LaserSource implements AttackBlockCallback {
	@Override
	public ActionResult interact(PlayerEntity player, @NotNull World world, Hand hand, BlockPos pos, Direction direction) {
		// Check if the target block is a laser source
		BlockState state = world.getBlockState(pos);
		//TODO
		// player.getName().getString().equals("Deployer")
		// player.getStackInHand(hand).isEmpty()
		if (!(state.isIn(TagKey.of(Registry.BLOCK_KEY, Cabricality.id("laser_source")))))
			return ActionResult.PASS;
		CabfDebugger.debug("Laser source detected");

		ArrayList<Direction> availableDirections = new ArrayList<>();
		for (Direction director : Direction.values()) {
			if (director == direction) continue;
			if (world.getBlockState(pos.offset(director)).getBlock() instanceof DirectionalDiodeLampBlock lamp && LaserProperties.generate(world.getBlockState(pos.offset(director)), lamp, 1) != null)
				availableDirections.add(director);
		}

		if (availableDirections.isEmpty()) return ActionResult.PASS;

		for (Direction director : availableDirections) {
			CabfDebugger.debug("Available direction: " + director.toString());
			BlockPos startPos = pos.offset(director);
			LaserProperties properties = Objects.requireNonNull(LaserProperties.generate(world.getBlockState(startPos), (DirectionalDiodeLampBlock) world.getBlockState(startPos).getBlock(), availableDirections.size()));
			for (float i = 0.0F; i < properties.length(); i += 0.1F) {
				double x = startPos.getX() + 0.5D + (director.getOffsetX() * i);
				double y = startPos.getY() + 0.5D + (director.getOffsetY() * i);
				double z = startPos.getZ() + 0.5D + (director.getOffsetZ() * i);
				world.addParticle(properties.toDustParticleEffect(), x, y, z, 0.0D, 0.0D, 0.0D);
			}
			LaserBehaviours.attackNearby(world, startPos.offset(director, properties.length()), properties.power());
			world.playSound(startPos.getX() + 0.5D, startPos.getY() + 0.5D, startPos.getZ() + 0.5D, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.BLOCKS, 0.55F, 0.5F, false);
		}

		return ActionResult.SUCCESS;
	}

	public static void load() {
		AttackBlockCallback.EVENT.register(new LaserSource());
	}
}
