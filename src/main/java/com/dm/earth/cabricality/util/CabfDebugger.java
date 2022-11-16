package com.dm.earth.cabricality.util;

import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.command.api.client.QuiltClientCommandSource;

import com.dm.earth.cabricality.Cabricality;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.text.Text;

public class CabfDebugger {
	private static boolean debug = true;

	public static void debug(String bug) {
		if (debug || QuiltLoader.isDevelopmentEnvironment())
			Cabricality.LOGGER.info("[Cabricality/DEBUG] " + bug);
	}

	public static class DebugCommand implements Command<QuiltClientCommandSource> {

		@Override
		public int run(CommandContext<QuiltClientCommandSource> context) throws CommandSyntaxException {
			if (debug) {
				debug = false;
				context.getSource().sendFeedback(Text.of("Debug mode disabled"));
			} else {
				debug = true;
				context.getSource().sendFeedback(Text.of("Debug mode enabled"));
			}
			return 1;
		}
		
	}
}
