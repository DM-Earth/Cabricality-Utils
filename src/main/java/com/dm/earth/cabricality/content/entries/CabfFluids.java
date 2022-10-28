package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.resin.ResinFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class CabfFluids {
    public static final Fluid RESIN = new ResinFluid.Still();
    public static final Fluid RESIN_FLOWING = new ResinFluid.Flowing();

    public static void register() {
        registerFluid("resin", RESIN, RESIN_FLOWING);
    }

    private static void registerFluid(String name, Fluid fluid) {
        Registry.register(Registry.FLUID, Cabricality.asIdentifier(name), fluid);
    }

    private static void registerFluid(String name, Fluid still, Fluid flowing) {
        registerFluid(name, still);
        registerFluid(name + "_flowing", flowing);
    }
}
