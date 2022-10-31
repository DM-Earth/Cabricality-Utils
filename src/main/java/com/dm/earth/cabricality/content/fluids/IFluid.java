package com.dm.earth.cabricality.content.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.minecraft.ClientOnly;

public interface IFluid {
    @ClientOnly void setupRendering();

    Identifier getId();

    Fluid getTypical();

    Fluid getFlowing();
}
