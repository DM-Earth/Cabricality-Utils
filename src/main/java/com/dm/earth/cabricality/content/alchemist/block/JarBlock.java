package com.dm.earth.cabricality.content.alchemist.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.dm.earth.cabricality.resource.ResourcedBlock;
import com.dm.earth.cabricality.util.VoxelShapeUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import net.devtech.arrp.json.blockstate.JBlockStates;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class JarBlock extends Block implements ResourcedBlock {
	public JarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapeUtil.simpleBox(4, 0, 4, 12, 14, 12);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(VoxelShapeUtil.simpleBox(4, 0, 4, 12, 12, 12), VoxelShapeUtil.simpleBox(5, 12, 5, 11, 14, 11));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.offset(Direction.DOWN)).isSideSolidFullSquare(world, pos.offset(Direction.DOWN), Direction.UP);
	}

	@Override
	public boolean doLootTable() {
		return true;
	}

	@Override
	public boolean doBlockStates() {
		return true;
	}

	@Override
	public boolean doItemModel() {
		return true;
	}

	@Override
	public @Nullable JBlockStates getBlockStates() {
		return JBlockStates.simple(this.getBlockModelId());
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block/jar/jar");
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack stack = player.getStackInHand(hand);
		if (player.isSneaking() || player.getStackInHand(hand).isEmpty() || world.isClient()) return ActionResult.PASS;
		Reagent reagent = null;
		for (Reagents reagents : Reagents.values()) {
			boolean breaked = false;
			for (Reagent reagentT : reagents.getReagents())
				if (reagentT.getItem() == stack.getItem()) {
					reagent = reagentT;
					breaked = true;
					break;
				}
			if (breaked) break;
		}
		if (reagent == null) return ActionResult.PASS;
		stack.decrement(1);
		player.setStackInHand(hand, stack);
		world.setBlockState(pos, Registry.BLOCK.get(Cabricality.id("reagent_jar_" + reagent.hashString())).getDefaultState());
		return ActionResult.SUCCESS;
	}
}
