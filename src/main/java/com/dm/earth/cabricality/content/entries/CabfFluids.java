package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.fluid.FluidBlockStatesGenerator;
import com.dm.earth.cabricality.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.cabricality.content.fluids.NumberFluid;
import com.dm.earth.cabricality.content.fluids.core.BaseFlowableFluid;
import com.dm.earth.cabricality.content.fluids.core.BaseFluid;
import com.dm.earth.cabricality.content.fluids.core.IFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class CabfFluids {

    public static final Fluid RESIN = new BaseFluid("resin");
    public static final Fluid REDSTONE = new BaseFluid("redstone");
    public static final Fluid WASTE = new BaseFluid("waste");
    public static final Fluid SKY_STONE = new BaseFluid("sky_stone");
    public static final Fluid COKE = new BaseFluid("coke");
    public static final Fluid FINE_SAND = new BaseFluid("fine_sand");

    public static final List<Fluid> NUMBERS = getNumberFluids();

    private static List<Fluid> getNumberFluids() {
        List<Integer> colors = List.of(0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8, 0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8);
        ArrayList<Fluid> numbers = new ArrayList<>();
        int i = 0;
        for (int color : colors) {
            i++;
            numbers.add(new NumberFluid(i).color(color));
        }
        return numbers;
    }

    public static void register() {
        registerFluids(
                RESIN,
                REDSTONE,
                WASTE,
                SKY_STONE,
                COKE,
                FINE_SAND
        );
        registerFluids(NUMBERS);
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

    private static void registerFluids(List<Fluid> fluids) {
        for (Fluid fluid : fluids) registerIFluid(fluid);
    }
}
