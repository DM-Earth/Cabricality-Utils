package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;

public abstract class AbstractTradeCardItem extends Item {

	public AbstractTradeCardItem(Settings settings) {
		super(settings);
	}

	public abstract String getContentString();

	@Override
	public String getTranslationKey() {
		return new TranslatableText(CabricalityClient.genTranslationKey("item", this.getType())).getString() + " §r- " + this.getContentString();
	}

	public abstract String getType();
}
