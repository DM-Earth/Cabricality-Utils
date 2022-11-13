package com.dm.earth.cabricality.content.alchemist;

import com.dm.earth.cabricality.content.alchemist.block.CatalystJarBlock;
import com.dm.earth.cabricality.content.alchemist.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.substrate.Catalyst;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

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
		for (Reagents reagents : values()) {
			for (Reagent reagent : reagents.getReagents()) {
				if (Objects.equals(reagent.hashString(), hashStr)) {
					return reagent;
				}
			}
		}
		return null;
	}

	@Nullable
	public static Catalyst getCatalystFromHash(String hashStr) {
		for (Reagents reagents : values()) {
			if (Objects.equals(reagents.getCatalyst().hashString(), hashStr)) {
				return reagents.getCatalyst();
			}
		}
		return null;
	}

	@NotNull
	public static Reagent getReagentFromBlock(ReagentJarBlock block) {
		return Objects.requireNonNull(getReagentFromHash(Registry.BLOCK.getId(block).getPath().replaceAll("reagent_jar_", "")));
	}

	@NotNull
	public static Catalyst getCatalystFromBlock(CatalystJarBlock block) {
		return Objects.requireNonNull(getCatalystFromHash(Registry.BLOCK.getId(block).getPath().replaceAll("catalyst_jar_", "")));
	}

	public String getName() {
		return name;
	}

	public int getTint() {
		return tint;
	}

	public List<Reagent> getReagents() {
		return entries;
	}

	public Catalyst getCatalyst() {
		return catalyst;
	}
}
