package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.fluid.FluidBlockStatesGenerator;
import com.dm.earth.cabricality.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.cabricality.content.fluids.BaseFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class CabfFluids {
    public static final Fluid RESIN = new BaseFluid() {
        @Override
        public Item getBucketItem() {
            return CabfItems.RESIN_BUCKET;
        }
    };

    public static void register() {
        registerFluid("resin", RESIN);
    }

    private static void registerFluidModeled(String name, Fluid still, Fluid flowing) {
        Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(name), Cabricality.id(name));
        Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(name + "_flowing"), Cabricality.id(name));
        Cabricality.CLIENT_RESOURCES.addModel(FluidModelGenerator.simple(name + "_still"), Cabricality.id("block/fluid/" + name));
        registerFluid(name, still, flowing);
    }

    private static void registerFluid(String name, Fluid fluid) {
        Registry.register(Registry.FLUID, Cabricality.id(name), fluid);
        if (fluid instanceof FlowableFluid flowable) CabfBlocks.registerFluidBlock(name, flowable);
    }

    private static void registerFluid(String name, Fluid still, Fluid flowing) {
        registerFluid(name, still);
        registerFluid(name + "_flowing", flowing);
    }
}
