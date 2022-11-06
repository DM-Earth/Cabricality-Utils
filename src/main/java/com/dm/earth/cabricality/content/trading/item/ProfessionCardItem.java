package com.dm.earth.cabricality.content.trading.item;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.trading.util.ProfessionUtil;
import net.minecraft.text.TranslatableText;

public class ProfessionCardItem extends AbstractTradeCardItem {
	public ProfessionCardItem(Settings settings) {
		super(settings);
	}

	@Override
	@SuppressWarnings("ConstantConditions")
	public String getContentString() {
		return new TranslatableText(CabricalityClient.genTranslationKey("profession", ProfessionUtil.fromItem(this).id().getPath())).getString();
	}

	@Override
	public String getType() {
		return "profession_card";
	}
}
