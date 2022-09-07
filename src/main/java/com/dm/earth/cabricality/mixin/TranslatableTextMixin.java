package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.CabricalityClient;
import com.dm.earth.cabricality.ModChecker;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TranslatableText.class)
public class TranslatableTextMixin {
    @ModifyVariable(method = "<init>(Ljava/lang/String;)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static String injected(String key) {
        String returnText = key;
        if (!ModChecker.isFullLoaded() && !key.startsWith(CabricalityClient.genTranslationKey("util", ""))){
            if (new TranslatableText(CabricalityClient.genTranslationKey("util", "upside_down")).getString().equals("false")){
                returnText = new TranslatableText(CabricalityClient.genTranslationKey("util", "missing_mod")).getString() + " " + ModChecker.getMods();
            } else if (new TranslatableText(CabricalityClient.genTranslationKey("util", "upside_down")).getString().equals("true")){
                returnText = ModChecker.getMods() + " " + new TranslatableText(CabricalityClient.genTranslationKey("util", "missing_mod")).getString();
            }
        }

        if (key.startsWith(CabricalityClient.genTranslationKey("item", "trade_card_"))) returnText = CabricalityClient.genTranslationKey("item", "trade_card");
        if (key.startsWith(CabricalityClient.genTranslationKey("item", "profession_card_"))) returnText = CabricalityClient.genTranslationKey("item", "profession_card");

        return returnText;
    }
}
