package com.dm.earth.cabricality.content.alchemist;

import static com.dm.earth.cabricality.ModEntry.AE2;
import static com.dm.earth.cabricality.ModEntry.CR;
import static com.dm.earth.cabricality.ModEntry.IV;
import static com.dm.earth.cabricality.ModEntry.MC;
import static com.dm.earth.cabricality.ModEntry.PMD;
import static com.dm.earth.cabricality.content.alchemist.substrate.Reagent.of;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

//TODO: fill this out
public enum Reagents {
	IGNEOUS("igneous", 0x6c8191, true,
			of("andesite", MC.id("andesite"), 0x868887),
			of("diorite", MC.id("diorite"), 0xe6e2e6),
			of("granite", MC.id("granite"), 0x9e6b5a),
			of("cobblestone", MC.id("cobblestone"), 0xa6a6a6),
			of("basalt", MC.id("basalt"), 0x32333D),
			of("tuff", MC.id("tuff"), 0x85837b),
			of("limestone", CR.id("limestone"), 0xbbb6a9),
			of("scoria", CR.id("scoria"), 0x493a34),
			of("blunite", PMD.id("blunite"), 0x5f6874),
			of("carbonite", PMD.id("carbonite"), 0x514e52)),

	CHAOTIC("chaos", 0xb200ed, false,
			of("silver", IV.id("silver_dust"), 0x9fadb4),
			of("silicon", AE2.id("silicon"), 0x85837b));

	private final String name;
	private final int tint;
	private final List<Reagent> entries;
	private final Catalyst catalyst;
	private final boolean link;

	Reagents(String name, int tint, boolean link, Reagent... reagents) {
		this.name = name;
		this.tint = tint;
		this.entries = List.of(reagents);
		this.catalyst = Catalyst.of(name, tint);
		this.link = link;
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
			if (reagents.getCatalyst().hashString().equals(hashStr))
				return reagents.getCatalyst();
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

	@Contract(pure = true)
	public boolean isLinked() {
		return link;
	}

	public static List<Block> getJarBlocks(boolean includeBlank) {
		ArrayList<Block> list = new ArrayList<>();
		for (Map.Entry<RegistryKey<Block>, Block> set : Registry.BLOCK.getEntries()) {
			Block block = set.getValue();
			if (block instanceof SubstrateJarBlock || (includeBlank && block instanceof JarBlock))
				list.add(block);
		}
		return list;
	}

	@Nullable
	public static Reagents get(Catalyst catalyst) {
		for (Reagents reagents : values())
			if (reagents.getCatalyst().equals(catalyst))
				return reagents;
		return null;
	}

	@Nullable
	public static Reagents get(Reagent reagent) {
		for (Reagents reagents : values())
			for (Reagent reagent1 : reagents.getReagents())
				if (reagent1.equals(reagent))
					return reagents;
		return null;
	}

	public static void load() {
	}
}
