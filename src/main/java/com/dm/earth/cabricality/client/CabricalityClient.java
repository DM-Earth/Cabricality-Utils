package com.dm.earth.cabricality.client;

import com.dm.earth.cabricality.content.fluids.core.IFluid;
import com.dm.earth.cabricality.util.ModChecker;
import net.minecraft.fluid.Fluid;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import java.util.List;

import static com.dm.earth.cabricality.Cabricality.ID;
import static com.dm.earth.cabricality.content.entries.CabfFluids.*;

public class CabricalityClient implements ClientModInitializer {

    public static String genTranslationKey(String type, String path) {
        return type + "." + ID + "." + path;
    }

    @Override
    public void onInitializeClient(ModContainer mod) {
        ModChecker.check();
        renderFluidInit();
    }

    private static void renderFluidInit() {
        renderFluids(RESIN, REDSTONE, WASTE, SKY_STONE, COKE, FINE_SAND, MATRIX, RAW_LOGIC);
        renderFluids(POWERED_WATER, POWERED_WATER_FLOWING);
        renderFluids(MOLTEN_DESH, MOLTEN_DESH_FLOWING, MOLTEN_OSTRUM, MOLTEN_OSTRUM_FLOWING, MOLTEN_CALORITE, MOLTEN_CALORITE_FLOWING);
        renderFluids(NUMBERS);
    }

    private static void renderFluid(Fluid fluid) {
        if (fluid.isStill(null)) {
            IFluid iFluid = (IFluid) fluid;
            iFluid.setupRendering();
        }
    }

    private static void renderFluids(Fluid... fluids) {
        for (Fluid fluid : fluids) renderFluid(fluid);
    }

    private static void renderFluids(List<Fluid> fluids) {
        for (Fluid fluid : fluids) renderFluid(fluid);
    }
}
