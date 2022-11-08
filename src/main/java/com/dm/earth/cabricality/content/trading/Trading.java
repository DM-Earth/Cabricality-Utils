package com.dm.earth.cabricality.content.trading;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.recipe.gen.JTradingRecipeGenerator;
import com.dm.earth.cabricality.util.CabfDebugger;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;

public class Trading {
	private static void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (Professions rawProfession : Professions.values()) {
			Profession profession = rawProfession.get();
			for (TradingEntry entry : profession.entries()) {
				CabfDebugger.debug("Registering recipe handler: " + entry.hashString());
				handler.register(Cabricality.id("trading/buy/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateBuy(entry)));
				handler.register(Cabricality.id("trading/sell/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateSell(entry)));
			}
		}
	}

	private static void removeRecipes(RecipeLoadingEvents.RemoveRecipesCallback.RecipeHandler handler) {
		CabfDebugger.debug("Removing infuse recipes!");
		var infuse = Registry.RECIPE_TYPE.get(new Identifier("indrev", "infuse"));
		handler.removeIf(infuse, p -> !p.getId().getNamespace().equals("cabricality"));
	}

	public static void load() {
		Professions.load();
		RecipeManagerHelper.addRecipes(Trading::addRecipes);
		RecipeManagerHelper.removeRecipes(Trading::removeRecipes);
	}
}
