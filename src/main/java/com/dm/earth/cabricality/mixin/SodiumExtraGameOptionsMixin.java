package com.dm.earth.cabricality.mixin;

import me.flashyreese.mods.sodiumextra.client.gui.SodiumExtraGameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SodiumExtraGameOptions.class)
public class SodiumExtraGameOptionsMixin {
    @Inject(method = "hasSuggestedRSO", at = @At("RETURN"), cancellable = true)
    private void hasSuggestedRSO(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
