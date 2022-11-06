package com.dm.earth.cabricality.client.listener;

import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ItemColorRegistryListener {
	@SuppressWarnings("ConstantConditions")
	public static void load() {
		ColorProviderRegistry.ITEM.register((itemStack, tintIndex) -> {
			if (itemStack.getItem() instanceof AbstractTradeCardItem card && tintIndex > 0)
				return TradingEntryRegistry.fromItem(card).getTint();
			return 0;
		});
	}
}
