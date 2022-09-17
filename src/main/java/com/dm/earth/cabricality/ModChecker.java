package com.dm.earth.cabricality;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModChecker {
    private static List<String> missingModList = new ArrayList<>();
    private static Map<String, String> requiredModList = new HashMap<>();

    public static void check(){
        if (isFullLoaded()) {
            if (requiredModList.isEmpty()) {

                //Write mods here
                requiredModList.putAll(ImmutableMap.of(
                        "ftblibrary", "FTB Library",
                        "ftbquests", "FTB Quests",
                        "ftbteams", "FTB Teams",
                        "questsadditions", "Quests Additions"
                ));
            }
            for (Map.Entry<String, String> modEntry : requiredModList.entrySet()) {
                if (!FabricLoader.getInstance().isModLoaded(modEntry.getKey()) && !missingModList.contains(modEntry.getValue()))
                    missingModList.add(modEntry.getValue());
            }
        }
    }

    @Nullable
    public static String getMods(){
        if (isFullLoaded()) return null;
        String mods = "";
        for (String mod : missingModList){
            if (!mods.equals("")){
                mods = mods + ", " + mod;
            } else {
                mods = mod;
            }
        }
        return mods;
    }

    public static Boolean isFullLoaded(){
        return missingModList.isEmpty();
    }
}
