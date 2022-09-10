package com.dm.earth.cabricality;

import net.fabricmc.api.ClientModInitializer;

import static com.dm.earth.cabricality.Cabricality.MODID;

public class CabricalityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModChecker.check();
	}

	public static String genTranslationKey(String type, String path) {
		return type + "." + MODID + "." + path;
	}
}
