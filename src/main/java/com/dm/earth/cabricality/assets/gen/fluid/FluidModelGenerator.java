package com.dm.earth.cabricality.assets.gen.fluid;

import com.dm.earth.cabricality.Cabricality;
import net.devtech.arrp.json.models.JModel;

public class FluidModelGenerator {
    public static JModel simple(String id) {
        return new JModel().addTexture("particle", Cabricality.id("fluid/" + id).toString());
    }
}
