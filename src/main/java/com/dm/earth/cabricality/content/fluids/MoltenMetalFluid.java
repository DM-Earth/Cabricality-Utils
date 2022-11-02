package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.client.FluidRendererRegistry;
import com.dm.earth.cabricality.content.fluids.core.BaseFlowableFluid;

public class MoltenMetalFluid extends BaseFlowableFluid {
    public MoltenMetalFluid(String metalName, boolean isStill, int color) {
        super("molten_" + metalName, isStill);
        color(color);
    }

    @Override
    public void setupRendering() {
        if (this.isStill(null))
            FluidRendererRegistry.register(this.getName(), "molten_metal", this.getTypical(), this.getFlowing(), true);
    }

    public static class Still extends MoltenMetalFluid {
        public Still(String metalName, int color) {
            super(metalName, true, color);
        }
    }

    public static class Flowing extends MoltenMetalFluid {
        public Flowing(String metalName, int color) {
            super(metalName, false, color);
        }
    }
}
