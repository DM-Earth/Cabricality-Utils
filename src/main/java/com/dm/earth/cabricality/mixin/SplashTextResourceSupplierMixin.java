package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.ModChecker;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method = "get", at = @At("RETURN"), cancellable = true)
    private void injected(CallbackInfoReturnable<String> cir) {
        if (!ModChecker.isFullLoaded()) {
            cir.setReturnValue(null);
        }
    }
}
