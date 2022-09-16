package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.DataFixerUtils;
import com.google.common.collect.ImmutableMap;
import net.minecraft.datafixer.Schemas;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(Schemas.class)
public class SchemasMixin {

    @ModifyVariable(method = "build", at = @At("TAIL"), name = "immutableMap3")
    private static ImmutableMap<String, String> CabricalitySchemasDFU(ImmutableMap<String, String> inputMap) {
        ImmutableMap<String, String> map = DataFixerUtils.read();
        if (!map.isEmpty()) {
            Map<String, String> normalMap = new HashMap<>(Map.copyOf(map));
            normalMap.putAll(inputMap);
            return ImmutableMap.copyOf(normalMap);
        }
        return inputMap;
    }
}
