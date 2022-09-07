package com.dm.earth.cabricality;

import net.fabricmc.api.DedicatedServerModInitializer;

public class CabricalityServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ModChecker.check();
        if (!ModChecker.isFullLoaded()) throw new RuntimeException(ModChecker.getMods() + " is missing for Cabricality Modpack!");
    }
}
