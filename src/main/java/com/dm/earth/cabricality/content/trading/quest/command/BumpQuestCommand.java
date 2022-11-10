package com.dm.earth.cabricality.content.trading.quest.command;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import java.util.ArrayList;

public class BumpQuestCommand implements Command<QuiltClientCommandSource> {
	@Override
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ArrayList<String> list = new ArrayList<>();

		int group = 0;
		int row = 0;
		boolean down = false;
		for (Professions p : Professions.values()) {
			Profession profession = p.get();

			group++;
			row += 2;
			if (group >= Professions.values().length / 2 - 1 && group <= Professions.values().length / 2 && !down) {
				row += 3;
				down = true;
			}

			int col = -3;

			list.add(generateQuest(-4, row, new TradingEntry(Cabricality.id("profession_card_" + profession.hashString()), 1, TradingEntry.CoinTypes.GOLD.getId(), 1, profession.tint())));

			for (TradingEntry entry : profession.entries()) {
				if (col > 4) {
					col = -4;
					row++;
				}
				list.add(generateQuest(col, row, entry));
				col++;
			}
		}

		MinecraftClient.getInstance().keyboard.setClipboard(String.join("\n\n", list));
		context.getSource().sendFeedback(Text.of("Copied to clipboard!"));
		return SINGLE_SUCCESS;
	}

	@NotNull
	private static String generateQuest(int x, int y, @NotNull TradingEntry entry) {
		return "{\n" +
				"title: \"" + entry.getItemCount() + " × {" + entry.getItem().getTranslationKey() + "}\"\n" +
				"icon: \"" + entry.getItemId() + "\"\n" +
				"disable_toast: true\n" +
				"x: " + x + "d\n" +
				"y: " + y + "d\n" +
				"shape: \"hexagon\"\n" +
				"subtitle: \"" + entry.getCoinCount() + " x {" + entry.getCoin().getTranslationKey() + "}\"\n" +
				"tasks: [{\ntype: \"item\"\n" +
				"item: \"" + entry.getItemId() + "\"\n" +
				"icon: \"" + entry.getItemId() + "\"\n" +
				"count: " + entry.getItemCount() + "L\n" +
				"}]\nrewards: [\n{\ntype: \"item\"\nauto: \"enabled\"\n" +
				"item: \"" + "cabricality:trade_card_" + entry.hashString() + "\"\n}\n{\ntype: \"custom\"\n" +
				"title: \"{market.cabricality.shipments.repeatable}\"\n" +
				"icon: \"indrev:module_charger\"\ntags: [\"reset\"]\nauto: \"no_toast\"\n}\n]\n}";
	}
}
