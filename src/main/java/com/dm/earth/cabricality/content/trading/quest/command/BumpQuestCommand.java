package com.dm.earth.cabricality.content.trading.quest.command;

import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import java.util.ArrayList;

public class BumpQuestCommand implements Command<QuiltClientCommandSource> {
	@Override
	public int run(CommandContext<QuiltClientCommandSource> context) {
		ArrayList<String> list = new ArrayList<>();

		int row = 0;
		for (Professions p : Professions.values()) {
			Profession profession = p.get();
			row++;
			if (row >= Professions.values().length / 2 - 1 && row <= Professions.values().length / 2) row += 3;
			for (TradingEntry entry : profession.entries()) {
			}
		}
		return SINGLE_SUCCESS;
	}
}
