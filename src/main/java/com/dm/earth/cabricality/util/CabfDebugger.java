package com.dm.earth.cabricality.util;

import com.dm.earth.cabricality.Cabricality;

public class CabfDebugger {
    private static final boolean DEBUG = false;

    public static void debug(String bug) {
        if (DEBUG)
            Cabricality.LOGGER.info("[Cabricality/DEBUG] " + bug);
    }
}
