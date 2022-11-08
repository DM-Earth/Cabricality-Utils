package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.recipe.gen.JTradingRecipeGenerator;
import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nhoryzon.mc.farmersdelight.recipe.CuttingBoardRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Inject(method = "deserialize", at = @At("RETURN"))
	private static void read(Identifier id, JsonObject json, CallbackInfoReturnable<Recipe<?>> cir) {
		if (cir.getReturnValue() instanceof CuttingBoardRecipe boardRecipe)
			DeployerCuttingRecipeHandler.cuttingBoardRecipes.add(boardRecipe);
	}
}
