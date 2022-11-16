package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.util.CabfDebugger.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.laser.LaserCore;
import com.dm.earth.cabricality.content.alchemist.laser.LaserProperties;
import com.dm.earth.cabricality.content.alchemist.substrate.Catalyst;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;

public class Alchemist {
	public static void load() {
		Reagents.load();
		LaserCore.load();
	}

	public static void processChaoticRecipe(HopperMinecartEntity minecart, LaserProperties properties) {
		if (!(minecart.getWorld() instanceof ServerWorld))
			return;
		ServerWorld world = (ServerWorld) minecart.getWorld();

		HashMap<Integer, Reagent> reagents = new HashMap<>();
		HashMap<Integer, Catalyst> catalysts = new HashMap<>();

		// Scan the minecart for reagents and catalysts
		for (int i = 0; i < 5; i++) {
			ItemStack stack = minecart.getStack(i);
			if (stack.isEmpty())
				continue;
			if (stack.getItem() instanceof BlockItem blockItem) {
				Block block = blockItem.getBlock();
				if (block instanceof ReagentJarBlock jar)
					reagents.put(i, (Reagent) jar.getSubstrate());
				if (block instanceof CatalystJarBlock jar)
					catalysts.put(i, (Catalyst) jar.getSubstrate());
			}
		}

		HashMap<Integer, Item> addSlots = new HashMap<>();

		// Reagents -> Catalysts
		Map<Catalyst, ArrayList<Reagent>> possibleReagentMap = possibleReagentMap(world);
		ArrayList<Reagent> reagents2 = mapToList(reagents);
		Catalyst catalystTargetTemp = getMatchedCatalyst(reagents2, possibleReagentMap);
		debug(reagents2.toString());

		if (catalystTargetTemp != null)
			for (var i : reagents.keySet())
				addSlots.put(i, Registry.ITEM.get(Cabricality.id("catalyst_jar_" + catalystTargetTemp.hashString())));

		for (Integer slot : reagents.keySet())
			minecart.removeStack(slot);

		for (var entry : addSlots.entrySet())
			minecart.setStack(entry.getKey(), entry.getValue().getDefaultStack());

		return;
	}

	private static <T> ArrayList<T> mapToList(Map<Integer, T> map) {
		ArrayList<T> list = new ArrayList<>();
		Integer[] ints = (new ArrayList<>(map.keySet())).toArray(new Integer[0]);
		Arrays.sort(ints);
		for (int i : ints)
			list.add(map.get(i));
		return list;
	}

	@Nullable
	private static Catalyst getMatchedCatalyst(ArrayList<Reagent> existed,
			Map<Catalyst, ArrayList<Reagent>> map) {
		for (var entry : map.entrySet()) {
			if (entry.getValue().equals(existed))
				return entry.getKey();
		}
		return null;
	}

	private static Map<Catalyst, ArrayList<Reagent>> possibleReagentMap(ServerWorld world) {
		HashMap<Catalyst, ArrayList<Reagent>> map = new HashMap<>();
		for (Reagents reagentsEntry : Reagents.values()) {
			if (!reagentsEntry.isLinked())
				continue;
			map.put(reagentsEntry.getCatalyst(), randomSelect(reagentsEntry.getReagents(), 4, world.getSeed()));
		}
		return map;
	}

	private static <T> ArrayList<T> randomSelect(List<T> list, int max, long seed) {
		ArrayList<T> processList = new ArrayList<>(list);
		ArrayList<T> returnList = new ArrayList<>();
		while (!(processList.size() <= 0 || returnList.size() >= max)) {
			int index = randomIntSeeded(processList.size() - 1, seed);
			returnList.add(processList.get(index));
			processList.remove(index);
		}
		return returnList;
	}

	private static int randomIntSeeded(int max, long seed) {
		return ((Double) Math.ceil(((seed * 9301 + 49297) % 233280) / 233280.0 * max)).intValue();
	}

	public static class AlchemistInformationCommand implements Command<ServerCommandSource> {

		@Override
		public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
			ServerWorld world = context.getSource().getWorld();
			Map<Catalyst, ArrayList<Reagent>> reagentMap = possibleReagentMap(world);
			ArrayList<String> output = new ArrayList<>();
			for (var entry : reagentMap.entrySet())
				output.add(entry.getKey().toString() + " -> " + entry.getValue().toString());

			for (String string : output)
				context.getSource().sendFeedback(Text.of(string), false);

			return SINGLE_SUCCESS;
		}
	}
}
