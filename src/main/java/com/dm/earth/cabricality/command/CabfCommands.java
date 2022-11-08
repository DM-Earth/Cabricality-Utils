package com.dm.earth.cabricality.command;

import com.dm.earth.cabricality.content.trading.quest.command.BumpQuestCommand;
import com.mojang.brigadier.CommandDispatcher;
import org.quiltmc.qsl.command.api.client.ClientCommandRegistrationCallback;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import static org.quiltmc.qsl.command.api.client.ClientCommandManager.literal;

public class CabfCommands implements ClientCommandRegistrationCallback {
	@Override
	public void registerCommands(CommandDispatcher<QuiltClientCommandSource> dispatcher) {
		dispatcher.register(literal("cabricality").then(literal("bump-quests").executes(new BumpQuestCommand())));
	}
}
