package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.client.FluidRendererRegistry;
import com.dm.earth.cabricality.content.fluids.core.BaseFluid;

public class NumberFluid extends BaseFluid {
    private final int number;

    public NumberFluid(int number) {
        super("number_" + number);
        this.number = number;
    }

    @Override
    public boolean hasBucketItem() {
        return false;
    }

    @Override
    public void setupRendering() {
        FluidRendererRegistry.register(this.getName(), "number", this.getTypical(), this.getFlowing(), false);
    }
}
