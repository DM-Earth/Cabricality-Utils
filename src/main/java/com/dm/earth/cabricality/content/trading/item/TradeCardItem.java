package com.dm.earth.cabricality.content.trading.item;

public class TradeCardItem extends AbstractTradeCardItem {
	public TradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public String getType() {
		return "trade_card";
	}
}
