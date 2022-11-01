package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.fluid.FluidBlockStatesGenerator;
import com.dm.earth.cabricality.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.cabricality.content.fluids.BaseFlowableFluid;
import com.dm.earth.cabricality.content.fluids.BaseFluid;
import com.dm.earth.cabricality.content.fluids.IFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfFluids {

    public static final Fluid RESIN = new BaseFluid("resin");
    public static final Fluid REDSTONE = new BaseFluid("redstone");
    public static final Fluid WASTE = new BaseFluid("waste");
    public static final Fluid SKY_STONE = new BaseFluid("sky_stone");
    public static final Fluid COKE = new BaseFluid("coke");

    public static void register() {
        registerFluids(
                RESIN,
                REDSTONE,
                WASTE,
                SKY_STONE,
                COKE
        );
    }

    private static void registerIFluid(Identifier id, Fluid fluid) {
        Registry.register(Registry.FLUID, id, fluid);
        if (fluid instanceof FlowableFluid flowable) CabfBlocks.registerFluidBlock(id, flowable);
    }

    private static void registerIFluid(Fluid fluid) {
        IFluid iFluid = (IFluid) fluid;
        registerIFluid(iFluid.getId(), fluid);
        iFluid.registerBucketItem(Registry.ITEM);
        if (fluid instanceof BaseFlowableFluid flowable) {
            Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(flowable.getName()), flowable.getId());
            Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(flowable.getName() + "_flowing"), flowable.getId());
            Cabricality.CLIENT_RESOURCES.addModel(FluidModelGenerator.simple(flowable.getName() + "_still", flowable.getName()), Cabricality.id("block/fluid/" + flowable.getName()));
        }
    }

    private static void registerFluids(Fluid... fluids) {
        for (Fluid fluid : fluids) registerIFluid(fluid);
    }
}
