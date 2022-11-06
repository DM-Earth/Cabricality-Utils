package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.item.ItemStack;

public class TradeCardItem extends AbstractTradeCardItem {
	public TradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public String getContentString(ItemStack stack) {
		return TradingEntryRegistry.fromItem((AbstractTradeCardItem) stack.getItem()).getItem().getName().getString();
	}

	@Override
	public String getType() {
		return "trade_card";
	}
}
