package com.dm.earth.cabricality.content.trading.quest.command;

import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
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
			row++;
			if (group >= Professions.values().length / 2 - 1 && group <= Professions.values().length / 2 && !down) {
				row += 3;
				down = true;
			}

			int col = -4;
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

	private static String generateQuest(int x, int y, TradingEntry entry) {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append("title: \"").append(entry.getItemCount()).append(" × {").append(entry.getItem().getTranslationKey()).append("}\"\n");
		builder.append("icon: \"").append(entry.getItemId()).append("\"\n");
		builder.append("disable_toast: true\n");
		builder.append("x: ").append(x).append("d\n");
		builder.append("y: ").append(y).append("d\n");
		builder.append("shape: \"hexagon\"\n");
		builder.append("subtitle: \"").append(entry.getCoinCount()).append(" x {").append(entry.getCoin().getTranslationKey()).append("}\"\n");
		builder.append("tasks: [{\ntype: \"item\"\n");
		builder.append("item: \"").append(entry.getItemId()).append("\"\n");
		builder.append("icon: \"").append(entry.getItemId()).append("\"\n");
		builder.append("count: ").append(entry.getItemCount()).append("L\n");
		builder.append("]}\nrewards: [\n{\ntype: \"item\"\nauto: \"enabled\"\n");
		builder.append("item: \"" + "cabricality:trade_card_").append(entry.hashString()).append("\"\n}\n{\ntype: \"custom\"\n");
		builder.append("title: \"{market.cabricality.shipments.repeatable}\"\n");
		builder.append("icon: \"indrev:module_charger\"\ntags: [\"reset\"]\nauto: \"no_toast\"\n}\n]\n}");
		return null;
	}
}
