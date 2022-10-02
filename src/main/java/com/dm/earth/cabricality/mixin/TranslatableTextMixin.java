package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.CabricalityClient;
import com.dm.earth.cabricality.ModChecker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(TranslatableText.class)
public class TranslatableTextMixin {
    @ModifyVariable(method = "<init>(Ljava/lang/String;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static String injected(String key) {
        if (!ModChecker.isFullLoaded()
                && !key.startsWith(CabricalityClient.genTranslationKey("util", ""))
                && !key.equals("menu.quit")
        ) {
            return ModChecker.missingModCount() <= 1
                    ? CabricalityClient.genTranslationKey("util", "missing_mod")
                    : CabricalityClient.genTranslationKey("util", "missing_mods");
        }

        if (key.startsWith(CabricalityClient.genTranslationKey("item", "trade_card_")))
            return CabricalityClient.genTranslationKey("item", "trade_card");
        if (key.startsWith(CabricalityClient.genTranslationKey("item", "profession_card_")))
            return CabricalityClient.genTranslationKey("item", "profession_card");

        return key;
    }
}
