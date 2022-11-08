package com.dm.earth.cabricality.client.listener;

import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import com.dm.earth.cabricality.content.trading.item.TradeCardItem;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ItemColorRegistryListener {
	public static void load() {
		ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> {
			if (tintIndex > 0) {
				Item item = itemStack.getItem();
				if (item instanceof TradeCardItem card) return TradingEntryRegistry.fromItem(card).getTint();
				if (item instanceof ProfessionCardItem card) return Objects.requireNonNull(ProfessionUtil.fromItem(card), "The profession can't be null!").tint();
			}
			return -1;
		}, getCards());
	}

	private static Item[] getCards() {
		ArrayList<Item> list = new ArrayList<>();
		for (Map.Entry<RegistryKey<Item>, Item> set : Registry.ITEM.getEntries()) {
			Item item = set.getValue();
			if (item instanceof AbstractTradeCardItem) list.add(item);
		}
		return list.toArray(new Item[0]);
	}
}
