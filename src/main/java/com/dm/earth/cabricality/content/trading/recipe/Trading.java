package com.dm.earth.cabricality.content.trading.recipe;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.recipe.gen.JTradingRecipeGenerator;
import com.dm.earth.cabricality.util.CabfDebugger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

public class Trading {
	private static void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (Professions rawProfession : Professions.values()) {
			Profession profession = rawProfession.get();
			Item professionCard = Registry.ITEM.get(Cabricality.id("profession_card_" + profession.hashString()));
			handler.register(Cabricality.id("crafting/dupe/card/profession_card/" + profession.hashString()), id -> VanillaRecipeBuilders.shapelessRecipe(new ItemStack(professionCard, 2)).ingredient(professionCard).build(id, "dupe_cards"));
			for (TradingEntry entry : profession.entries()) {
				CabfDebugger.debug("Registering recipe handler: " + entry.hashString());
				handler.register(Cabricality.id("trading/buy/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateBuy(entry)));
				handler.register(Cabricality.id("trading/sell/" + profession.hashString() + "/" + entry.hashString()), id -> RecipeManager.deserialize(id, JTradingRecipeGenerator.generateSell(entry)));

				Item tradeCard = Registry.ITEM.get(Cabricality.id("trade_card_" + entry.hashString()));
				handler.register(Cabricality.id("crafting/dupe/card/trade_card/" + entry.hashString()), id -> VanillaRecipeBuilders.shapelessRecipe(new ItemStack(tradeCard, 2)).ingredient(tradeCard).build(id, "dupe_cards"));
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
