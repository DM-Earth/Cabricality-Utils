package com.dm.earth.cabricality.content.alchemist;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.JarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.SubstrateJarBlock;
import com.dm.earth.cabricality.content.alchemist.substrate.Catalyst;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;

import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.content.alchemist.substrate.Reagent.of;

//TODO: fill this out
public enum Reagents {
	IGNEOUS("igneous", 0x6c8191,
			of("andesite", MC.id("andesite"), 0x868887),
			of("diorite", MC.id("diorite"), 0xe6e2e6),
			of("granite", MC.id("granite"), 0x9e6b5a)
	);

	private final String name;
	private final int tint;
	private final List<Reagent> entries;
	private final Catalyst catalyst;

	Reagents(String name, int tint, Reagent... reagents) {
		this.name = name;
		this.tint = tint;
		this.entries = List.of(reagents);
		this.catalyst = Catalyst.of(name, tint);
	}

	@Nullable
	public static Reagent getReagentFromHash(String hashStr) {
		for (Reagents reagents : values())
			for (Reagent reagent : reagents.getReagents())
				if (reagent.hashString().equals(hashStr))
					return reagent;
		return null;
	}

	@Nullable
	public static Catalyst getCatalystFromHash(String hashStr) {
		for (Reagents reagents : values())
			if (reagents.getCatalyst().hashString().equals(hashStr)) return reagents.getCatalyst();
		return null;
	}

	@NotNull
	public static Reagent getReagentFromBlock(ReagentJarBlock block) {
		Reagent reagent = getReagentFromHash(Registry.BLOCK.getId(block).getPath().replaceAll("reagent_jar_", ""));
		if (reagent == null) {
			ArrayList<String> reagents = new ArrayList<>();
			for (Reagents reagents1 : Reagents.values())
				for (Reagent reagent1 : reagents1.getReagents())
					reagents.add(reagent1.toString());
			Cabricality.LOGGER.error("Reagents:");
			Cabricality.LOGGER.error(String.join(", ", reagents));
			throw new EnumConstantNotPresentException(Reagents.class, Registry.BLOCK.getId(block).toString());
		}
		return reagent;
	}

	@NotNull
	public static Catalyst getCatalystFromBlock(CatalystJarBlock block) {
		Catalyst catalyst = getCatalystFromHash(Registry.BLOCK.getId(block).getPath().replaceAll("catalyst_jar_", ""));
		if (catalyst == null) {
			ArrayList<String> catalysts = new ArrayList<>();
			for (Reagents reagents : Reagents.values())
				catalysts.add(reagents.getCatalyst().toString());
			Cabricality.LOGGER.error("Catalysts:");
			Cabricality.LOGGER.error(String.join(", ", catalysts));
			throw new EnumConstantNotPresentException(Reagents.class, Registry.BLOCK.getId(block).toString());
		}
		return catalyst;
	}

	@Contract(pure = true)
	public String getName() {
		return name;
	}

	@Contract(pure = true)
	public int getTint() {
		return tint;
	}

	@Contract(pure = true)
	public List<Reagent> getReagents() {
		return entries;
	}

	@Contract(pure = true)
	public Catalyst getCatalyst() {
		return catalyst;
	}

	public static List<Block> getJarBlocks(boolean includeBlank) {
		ArrayList<Block> list = new ArrayList<>();
		for (Map.Entry<RegistryKey<Block>, Block> set : Registry.BLOCK.getEntries()) {
			Block block = set.getValue();
			if (block instanceof SubstrateJarBlock || (includeBlank && block instanceof JarBlock)) list.add(block);
		}
		return list;
	}

	public static void load() {
	}
}
