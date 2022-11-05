package com.dm.earth.cabricality.client.util;

import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemColorRegistryListener {
	@SuppressWarnings("ConstantConditions")
	public static void load() {
		ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> {
			Identifier id = Registry.ITEM.getId(itemStack.getItem());
			if (itemStack.getItem() instanceof AbstractTradeCardItem card) {
				TradingEntry entry = TradingEntryRegistry.fromHashCode(Integer.parseInt(id.getPath().replaceAll(card.getType() + "_", "")));
				return entry.getTint();
			}
			return 0;
		});
	}
}
