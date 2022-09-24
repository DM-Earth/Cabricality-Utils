package com.dm.earth.cabricality.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class DataFixerUtils {
    private static final Map<String, String> dataFixerMapCommon = new HashMap<>();

    public static void addFixer(Identifier oldId, Identifier newId) {
        dataFixerMapCommon.put(oldId.toString(), newId.toString());
    }

    public static void addFixer(ImmutableMap<Identifier, Identifier> map) {
        for (Map.Entry<Identifier, Identifier> entry : map.entrySet()) {
            addFixer(entry.getKey(), entry.getValue());
        }
    }

    public static ImmutableMap<String, String> read() {
        return ImmutableMap.copyOf(dataFixerMapCommon);
    }
}
