package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public abstract class AbstractTradeCardItem extends Item {

	public AbstractTradeCardItem(Settings settings) {
		super(settings);
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public Text getName(ItemStack stack) {
		if (!stack.hasNbt()) return new TranslatableText(this.getTranslationKey(stack));
		return Text.of(new TranslatableText(this.getTranslationKey(stack)).getString() + " " + new TranslatableText(TradingEntryRegistry.fromNbt(stack.getNbt()).getItem().getTranslationKey()).getString());
	}

	@Override
	public String getTranslationKey() {
		return CabricalityClient.genTranslationKey("item", this.getType());
	}

	public abstract String getType();
}
