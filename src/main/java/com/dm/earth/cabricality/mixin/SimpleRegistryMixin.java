package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.DataFixerUtils;
import com.mojang.serialization.Lifecycle;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class SimpleRegistryMixin {
    @Inject(method = "set(ILnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;Lcom/mojang/serialization/Lifecycle;Z)Lnet/minecraft/util/registry/RegistryEntry;", at = @At("HEAD"))
    private <T> void injected(int rawId, RegistryKey<T> key, T value, Lifecycle lifecycle, boolean checkDuplicateKeys, CallbackInfoReturnable<RegistryEntry<T>> cir) {
        if (key.getValue().getNamespace().equals(Cabricality.MODID) && key.getClass() != null) {
            Identifier newId = key.getValue();
            DataFixerUtils.addFixer(new Identifier("kubejs", newId.getPath()), newId);
        }
    }
}
