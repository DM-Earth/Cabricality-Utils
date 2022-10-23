package com.dm.earth.cabricality.mixin;

import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(QuiltLoader.class)
public class QuiltLoaderMixin {
    @Inject(method = "isModLoaded", at = @At("RETURN"), cancellable = true)
    private static void isModLoaded(String id, CallbackInfoReturnable<Boolean> cir) {
        if (id.equals("reeses-sodium-options")) cir.setReturnValue(true);
    }
}
