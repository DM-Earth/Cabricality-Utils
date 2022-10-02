package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.CabricalityClient;
import com.dm.earth.cabricality.ModChecker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow @Final private RotatingCubeMapRenderer backgroundRenderer;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/TitleScreen;doBackgroundFade:Z"))
    public boolean renderRedirected(TitleScreen instance) {
        return false;
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void renderInjectedTail(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!ModChecker.isFullLoaded()) {
            Screen screen = MinecraftClient.getInstance().currentScreen;
            TextRenderer textRenderer = ((ScreenAccessor) screen).getTextRenderer();

            ArrayList<Text> TEXTS = new ArrayList<>();
            TEXTS.add(new TranslatableText(CabricalityClient.genTranslationKey("util", "warn")));

            if (ModChecker.checkMissingMod("FTB Library")) TEXTS.add(new LiteralText("§c<FTB Library>"));
            if (ModChecker.checkMissingMod("FTB Quests")) TEXTS.add(new LiteralText("§c<FTB Quests>"));
            if (ModChecker.checkMissingMod("FTB Teams")) TEXTS.add(new LiteralText("§c<FTB Teams>"));
            if (ModChecker.checkMissingMod("Quests Additions")) TEXTS.add(new LiteralText("§c<Quests Additions>"));

            int yMultiple = 0;
            float xyMultiple = 1.7f;

            for (Text text : TEXTS) {
                matrices.push();
                matrices.translate(width / 2, yMultiple += height / (TEXTS.size() + 1), 0.0);
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20.0f));
                float h = 1.8f - MathHelper.abs(MathHelper.sin((float)(Util.getMeasuringTimeMs() % 1000L) / 1000.0f * ((float)Math.PI * 2)) * 0.1f);
                h = h * 100.0f / (float)(textRenderer.getWidth(text) + 32);
                matrices.scale(h * xyMultiple, h * xyMultiple, h * xyMultiple);
                TitleScreen.drawCenteredText(matrices, textRenderer, text, 0, -8, 0xFF555555);
                matrices.pop();
            }
        }
    }
}
