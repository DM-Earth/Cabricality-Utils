package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.DataFixerUtils;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.Schemas;
import net.minecraft.datafixer.fix.ItemNameFix;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Mixin(Schemas.class)
public class SchemasMixin {
    @Shadow @Final private static BiFunction<Integer, Schema, Schema> EMPTY_IDENTIFIER_NORMALIZE;

    @Shadow
    private static UnaryOperator<String> replacing(Map<String, String> replacements) {
        return null;
    }

    @Inject(method = "build", at = @At("TAIL"))
    private static void CabricalitySchemasDFU(DataFixerBuilder builder, CallbackInfo ci) {
        ImmutableMap<String, String> map = DataFixerUtils.read();
        if (!map.isEmpty()) {
            Schema schemaCabricality = builder.addSchema(114514, EMPTY_IDENTIFIER_NORMALIZE);
            builder.addFixer(ItemNameFix.create(schemaCabricality, "Cabricality DataFixers", replacing(map)));
        }
    }
}
