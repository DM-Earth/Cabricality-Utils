package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.KubeJSRecipeWrapperUtil;
import com.google.common.base.Stopwatch;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.RecipeEventJS;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableInt;
import org.quiltmc.qsl.recipe.impl.RecipeManagerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeEventJS.class)
public class RecipeEventJSMixin {

	@Inject(method = "post", at = @At(value = "INVOKE", target = "Ljava/util/Map;entrySet()Ljava/util/Set;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	private void onReload(RecipeManager recipeManager, Map<Identifier, JsonElement> jsonMap, CallbackInfo ci, Stopwatch timer, JsonObject allRecipeMap) {
		RecipeManagerImpl.apply(jsonMap, KubeJSRecipeWrapperUtil.builderMap, KubeJSRecipeWrapperUtil.globalBuilder);
	}

	@Inject(method = "post", at = @At(value = "INVOKE", target = "Ldev/latvian/mods/kubejs/recipe/RecipePlatformHelper;pingNewRecipes(Ljava/util/Map;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	@SuppressWarnings("unchecked")
	private void onReloadEnd(RecipeManager recipeManager, Map<Identifier, JsonObject> jsonMap, CallbackInfo ci, Stopwatch timer, JsonObject allRecipeMap, MutableInt removed, MutableInt added, MutableInt failed, MutableInt fallbacked, HashMap recipesByName, HashMap newRecipeMap) {
		KubeJSRecipeWrapperUtil.build();
		recipesByName.putAll(KubeJSRecipeWrapperUtil.globalRecipes);
		newRecipeMap.putAll(KubeJSRecipeWrapperUtil.recipes);
		KubeJSRecipeWrapperUtil.clean();
	}
}
