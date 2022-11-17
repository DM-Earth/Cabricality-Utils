package com.dm.earth.cabricality.content.alchemist.block;

import com.dm.earth.cabricality.Cabricality;

import net.minecraft.util.Identifier;

public class ChaoticCatalystJarBlock extends CatalystJarBlock {

    public ChaoticCatalystJarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Identifier getBlockModelId() {
        return Cabricality.id("block/jar/chaos_catalyst");
    }

}
