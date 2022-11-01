package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.fluid.FluidBlockStatesGenerator;
import com.dm.earth.cabricality.assets.gen.fluid.FluidModelGenerator;
import com.dm.earth.cabricality.client.FluidRendererRegistry;
import com.dm.earth.cabricality.content.fluids.NumberFluid;
import com.dm.earth.cabricality.content.fluids.PoweredWater;
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
    // Fluids
    public static final List<Fluid> NUMBERS = getNumberFluids();
    public static final Fluid RESIN = new BaseFluid("resin");
    public static final Fluid REDSTONE = new BaseFluid("redstone");
    public static final Fluid WASTE = new BaseFluid("waste");
    public static final Fluid SKY_STONE = new BaseFluid("sky_stone");
    public static final Fluid COKE = new BaseFluid("coke");
    public static final Fluid FINE_SAND = new BaseFluid("fine_sand");
    public static final Fluid MATRIX = new BaseFluid("matrix") {
        @Override
        public boolean hasBucketItem() {
            return false;
        }
    };
    public static final Fluid RAW_LOGIC = new BaseFluid("raw_logic"){
        @Override
        public boolean hasBucketItem() {
            return false;
        }
    };

    // Flowable Fluids
    public static final FlowableFluid POWERED_WATER = new PoweredWater.Still();
    public static final FlowableFluid POWERED_WATER_FLOWING = new PoweredWater.Flowing();


    private static List<Fluid> getNumberFluids() {
        List<Integer> colors = List.of(0xCBE827, 0xAEE827, 0x68E827, 0x27E86E, 0x27E8B1, 0x27DEE8, 0x27B5E8, 0x2798E8, 0x2778E8, 0x2748E8);
        ArrayList<Fluid> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) numbers.add(new NumberFluid(i).color(colors.get(i + 1)));
        return numbers;
    }

    public static void register() {
        registerIFluids(RESIN, REDSTONE, WASTE, SKY_STONE, COKE, FINE_SAND, MATRIX, RAW_LOGIC);
        registerIFluids(POWERED_WATER, POWERED_WATER_FLOWING);
        registerIFluids(NUMBERS);
    }

    private static void registerFluid(Identifier id, Fluid fluid) {
        Registry.register(Registry.FLUID, id, fluid);
        if (!fluid.isStill(null)) CabfBlocks.registerFluidBlock(id, (FlowableFluid) fluid);
    }

    private static void registerIFluid(Fluid fluid) {
        IFluid iFluid = (IFluid) fluid;
        registerFluid(iFluid.getId(), fluid);
        iFluid.registerBucketItem(Registry.ITEM);
        if (fluid.isStill(null)) {
            Cabricality.CLIENT_RESOURCES.addBlockState(FluidBlockStatesGenerator.simple(iFluid.getName()), iFluid.getId());
            Cabricality.CLIENT_RESOURCES.addModel(FluidModelGenerator.simple(iFluid.getName() + "_still", iFluid.getName()), Cabricality.id("block/fluid/" + iFluid.getName()));
        }
    }

    private static void registerIFluids(Fluid... fluids) {
        for (Fluid fluid : fluids) registerIFluid(fluid);
    }

    private static void registerIFluids(List<Fluid> fluids) {
        for (Fluid fluid : fluids) registerIFluid(fluid);
    }
}
