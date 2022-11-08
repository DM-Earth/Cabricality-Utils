package com.dm.earth.cabricality.util;

import com.dm.earth.cabricality.Cabricality;
import org.quiltmc.loader.api.QuiltLoader;

public class CabfDebugger {
	private static final boolean DEBUG = false;

	public static void debug(String bug) {
		if (DEBUG || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[Cabricality/DEBUG] " + bug);
	}
}
