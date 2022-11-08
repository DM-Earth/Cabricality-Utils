package com.dm.earth.cabricality.content.trading.recipe.gen;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

public class JTradingRecipeGenerator {
	/*
	 * This method is used to generate a json object for a trading sell recipe.
	 * @param entry The trading entry to generate the json object for.
	 */
	public static JsonObject generateSell(TradingEntry entry) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:infuse");

		JsonArray ingredients = new JsonArray();
		ingredients.add(genItemEntry(Cabricality.id("profession_card_" + ProfessionUtil.fromTradingEntry(entry).hashString()), 0));
		ingredients.add(genItemEntry(entry.getItemId(), entry.getItemCount()));
		json.add("ingredients", ingredients);

		json.add("output", genItemEntry(entry.getCoinId(), entry.getCoinCount()));

		json.addProperty("processTime", 125);
		return json;
	}

	/*
	 * This method is used to generate a json object for a trading buy recipe.
	 * @param entry The trading entry to generate the json object for.
	 */
	public static JsonObject generateBuy(TradingEntry entry) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "indrev:infuse");

		JsonArray ingredients = new JsonArray();
		ingredients.add(genItemEntry(Cabricality.id("trade_card_" + entry.hashString()), 0));
		ingredients.add(genItemEntry(entry.getCoinId(), entry.getCoinCount()));
		json.add("ingredients", ingredients);

		json.add("output", genItemEntry(entry.getItemId(), entry.getItemCount()));

		json.addProperty("processTime", 125);
		return json;
	}

	private static JsonObject genItemEntry(Identifier id, int count) {
		JsonObject json = new JsonObject();
		json.addProperty("item", id.toString());
		json.addProperty("count", count);
		return json;
	}
}
