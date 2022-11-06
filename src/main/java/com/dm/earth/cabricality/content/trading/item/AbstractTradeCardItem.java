package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public abstract class AbstractTradeCardItem extends Item {

	public AbstractTradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		return Text.of(new TranslatableText(this.getTranslationKey(stack)).getString() + " " + this.getContentString(stack));
	}

	public abstract String getContentString(ItemStack stack);

	@Override
	public String getTranslationKey() {
		return CabricalityClient.genTranslationKey("item", this.getType());
	}

	public abstract String getType();
}
