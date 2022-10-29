package com.dm.earth.cabricality.content.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class BaseFlowableFluid extends FlowableFluid {

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return !state.getFluid().isStill(state);
    }

    @Override
    public int getTickRate(WorldView world) {
        return 10;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return Registry.BLOCK.get(getIdentifier()).getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    protected abstract Identifier getIdentifier();
}
