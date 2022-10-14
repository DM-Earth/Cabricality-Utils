package com.dm.earth.cabricality;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static com.dm.earth.cabricality.Cabricality.MODID;

public class CabricalityClient implements ClientModInitializer {

	public static String genTranslationKey(String type, String path) {
		return type + "." + MODID + "." + path;
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		ModChecker.check();
	}
}
