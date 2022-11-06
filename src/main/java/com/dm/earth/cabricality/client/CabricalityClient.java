package com.dm.earth.cabricality.client;

import com.dm.earth.cabricality.client.listener.ItemColorRegistryListener;
import com.dm.earth.cabricality.util.ModChecker;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import static com.dm.earth.cabricality.Cabricality.ID;

public class CabricalityClient implements ClientModInitializer {

	public static String genTranslationKey(String type, String path) {
		return type + "." + ID + "." + path;
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		ModChecker.check();
		FluidRendererRegistry.renderFluidInit();
		ItemColorRegistryListener.load();
	}
}
