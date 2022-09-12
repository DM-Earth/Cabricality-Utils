package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import dev.architectury.registry.registries.Registries;
import dev.latvian.mods.kubejs.KubeJSRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KubeJSRegistries.class)
public class KubeJSRegistriesMixin {
    @Shadow @Mutable @Final private static final Registries REGISTRIES = Cabricality.REGISTRIES_CABRICALITY;
}
