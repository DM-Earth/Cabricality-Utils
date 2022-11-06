package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import net.minecraft.item.ItemStack;

public class ProfessionCardItem extends AbstractTradeCardItem {
	public ProfessionCardItem(Settings settings) {
		super(settings);
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public String getContentString(ItemStack stack) {
		return CabricalityClient.genTranslationKey("profession", ProfessionUtil.fromItem((ProfessionCardItem) stack.getItem()).id().getPath());
	}

	@Override
	public String getType() {
		return "profession_card";
	}
}
