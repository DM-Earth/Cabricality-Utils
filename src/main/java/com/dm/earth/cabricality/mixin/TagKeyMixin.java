package com.dm.earth.cabricality.mixin;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TagKey.class)
public class TagKeyMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static Identifier modifyIdentifier(Identifier identifier) {
        return new Identifier(identifier.getNamespace().replace("forge", "c"), identifier.getPath());
    }
}
