package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.item.ItemModelGenerator;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfItems {
	public static void register() {
		// Trading Cards
		for (Professions professionEntry : Professions.values()) {
			Profession profession = professionEntry.get();
			registerItemModeled("profession_card_" + profession.hashString(), new ProfessionCardItem(Properties.CARD), ItemModelGenerator.parented(Cabricality.id("item/card/profession_card").toString()));
			for (TradingEntry entry : profession.entries())
				registerItemModeled("trade_card_" + entry.hashString(), new TradeCardItem(Properties.CARD), ItemModelGenerator.parented(Cabricality.id("item/card/trade_card").toString()));
		}

		// Coins
		for (TradingEntry.CoinTypes coinType : TradingEntry.CoinTypes.values())
			registerItemModeled(coinType.getId().getPath(), new Item(Properties.DEFAULT.maxCount(16)), ItemModelGenerator.generated("item/coin", coinType.getId().getPath()));
	}

	private static void registerItemModeled(String name, Item item, JModel model) {
		Cabricality.CLIENT_RESOURCES.addModel(model, Cabricality.id("item/" + name));
		registerItem(name, item);
	}

	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, Cabricality.id(name), item);
	}

	public static final class Properties {
		public static final Item.Settings DEFAULT = new QuiltItemSettings().group(Cabricality.MAIN_GROUP);
		public static final Item.Settings DEFAULT_SINGLE = DEFAULT.maxCount(1);
		public static final Item.Settings CARD = new QuiltItemSettings().maxCount(1);
		public static final Item.Settings JAR = new QuiltItemSettings().maxCount(4);
	}
}
