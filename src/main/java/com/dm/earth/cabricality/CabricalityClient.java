package com.dm.earth.cabricality;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CabricalityClient implements ClientModInitializer {
	public static final String MODID = "cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitializeClient() {
		ModChecker.check();
	}

	public static Identifier genIdentifier(String id) {
		return new Identifier(MODID, id);
	}

	public static String genTranslationKey(String type, String path) {
		return type + "." + MODID + "." + path;
	}
}
