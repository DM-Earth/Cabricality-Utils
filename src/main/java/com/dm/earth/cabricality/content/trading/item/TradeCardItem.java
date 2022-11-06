package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.text.TranslatableText;

public class TradeCardItem extends AbstractTradeCardItem {
	public TradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public String getContentString() {
		return new TranslatableText(TradingEntryRegistry.fromItem(this).getItem().getTranslationKey()).getString();
	}

	@Override
	public String getType() {
		return "trade_card";
	}
}
