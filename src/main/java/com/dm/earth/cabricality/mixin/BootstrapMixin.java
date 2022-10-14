package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.DataFixerListener;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    @Inject(method = "initialize", at = @At("TAIL"))
    private static void initialize(CallbackInfo info) {
        DataFixerListener.registerDataFixers();
    }
}
