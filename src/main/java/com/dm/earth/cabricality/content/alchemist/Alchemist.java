package com.dm.earth.cabricality.content.alchemist;

import java.util.ArrayList;
import java.util.Collection;
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

import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;

public class Alchemist {
	public static void load() {
		Reagents.load();
		LaserCore.load();
	}

	@Nullable
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
		Map<Catalyst, List<Reagent>> possibleReagentMap = possibleReagentMap(world);
		Catalyst catalystTargetTemp = getTargetMatchedCatalyst(reagents.values(), possibleReagentMap);
		if (catalystTargetTemp != null)
			for (var i : reagents.keySet())
				addSlots.put(i, Registry.ITEM.get(Cabricality.id("catalyst_jar_" + catalystTargetTemp.hashString())));

		for (Integer slot : reagents.keySet())
			minecart.removeStack(slot);

		for (var entry : addSlots.entrySet())
			minecart.setStack(entry.getKey(), entry.getValue().getDefaultStack());

		return;
	}

	@Nullable
	private static Catalyst getTargetMatchedCatalyst(Collection<Reagent> existed, Map<Catalyst, List<Reagent>> map) {
		for (var entry : map.entrySet()) {
			if (entry.getValue().equals(existed))
				return entry.getKey();
		}
		return null;
	}

	private static Map<Catalyst, List<Reagent>> possibleReagentMap(ServerWorld world) {
		HashMap<Catalyst, List<Reagent>> map = new HashMap<>();
		for (Reagents reagentsEntry : Reagents.values()) {
			if (!reagentsEntry.isLinked())
				continue;
			map.put(reagentsEntry.getCatalyst(), randomSelect(reagentsEntry.getReagents(), 4, world.getSeed()));
		}
		return map;
	}

	private static <T> List<T> randomSelect(List<T> list, int max, long seed) {
		ArrayList<T> processList = new ArrayList<>();
		ArrayList<T> returnList = new ArrayList<>();
		processList.addAll(list);
		while (processList.size() > max) {
			int index = randomIntSeeded(processList.size(), seed) - 1;
			returnList.add(processList.get(index));
			processList.remove(index);
		}
		return returnList;
	}

	private static int randomIntSeeded(int max, long seed) {
		return (int) Math.ceil(((seed * 9301 + 49297) % 233280) / 233280.0 * max);
	}
}
