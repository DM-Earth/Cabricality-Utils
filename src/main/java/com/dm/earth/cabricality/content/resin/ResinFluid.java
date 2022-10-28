package com.dm.earth.cabricality.content.resin;

import com.dm.earth.cabricality.content.core.BaseFluid;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.world.WorldView;

public abstract class ResinFluid extends BaseFluid {
    @Override
    public Fluid getFlowing() {
        return CabfFluids.RESIN_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return CabfFluids.RESIN;
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        if (world.getDimension().isUltrawarm()) return 0;
        return 1;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 4;
    }

    @Override
    public Item getBucketItem() {
        return CabfItems.RESIN_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return null;
    }

    public static class Still extends ResinFluid {

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }

    public static class Flowing extends ResinFluid {

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.getLevel();
        }
    }
}
