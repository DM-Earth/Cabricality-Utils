package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.fluid.FluidBlockStatesGenerator;
import com.dm.earth.cabricality.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.cabricality.content.fluids.BaseFlowableFluid;
import com.dm.earth.cabricality.content.fluids.BaseFluid;
import com.dm.earth.cabricality.content.fluids.IFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CabfFluids {

    public static final Fluid RESIN = new BaseFluid("resin");
    public static final Fluid REDSTONE = new BaseFluid("redstone");

    public static void register() {
        registerFluid(RESIN);
        registerFluid(REDSTONE);
    }

    private static void registerFluid(Identifier id, Fluid fluid) {
        Registry.register(Registry.FLUID, id, fluid);
        if (fluid instanceof FlowableFluid flowable) CabfBlocks.registerFluidBlock(id, flowable);
    }

    private static void registerFluid(Fluid fluid) {
        IFluid iFluid = (IFluid) fluid;
        registerFluid(iFluid.getId(), fluid);
        if (fluid instanceof BaseFlowableFluid flowable) {
            Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(flowable.getName()), flowable.getId());
            Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(flowable.getName() + "_flowing"), flowable.getId());
            Cabricality.CLIENT_RESOURCES.addModel(FluidModelGenerator.simple(flowable.getName() + "_still", flowable.getName()), Cabricality.id("block/fluid/" + flowable.getName()));
        }
    }
}
