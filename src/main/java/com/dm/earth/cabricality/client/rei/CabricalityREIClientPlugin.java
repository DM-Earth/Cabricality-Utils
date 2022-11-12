package com.dm.earth.cabricality.client.rei;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.util.CabfDebugger;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.CollapsibleEntryRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.text.TranslatableText;

import static com.dm.earth.cabricality.content.trading.data.tag.TradeTags.PROFESSION_CARDS;
import static com.dm.earth.cabricality.content.trading.data.tag.TradeTags.TRADE_CARDS;

@SuppressWarnings("UnstableApiUsage")
public class CabricalityREIClientPlugin implements REIClientPlugin {
	@Override
	public void registerCollapsibleEntries(CollapsibleEntryRegistry registry) {
		CabfDebugger.debug("Registering Collapsible Entries");

		registry.group(Cabricality.id("trade_cards"), new TranslatableText(CabricalityClient.genTranslationKey("tag", TRADE_CARDS.id().getPath())), EntryIngredients.ofItemTag(TRADE_CARDS));
		registry.group(Cabricality.id("profession_cards"), new TranslatableText(CabricalityClient.genTranslationKey("tag", PROFESSION_CARDS.id().getPath())), EntryIngredients.ofItemTag(PROFESSION_CARDS));
	}
}
