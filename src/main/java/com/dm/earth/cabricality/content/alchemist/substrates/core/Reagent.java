package com.dm.earth.cabricality.content.alchemist.substrates.core;

import com.dm.earth.cabricality.content.entries.CabfBlocks;
import net.minecraft.util.Identifier;

public class Reagent extends AbstractSubstrate {
	private Reagent(Identifier id, int tint) {
		super(id, tint);
	}

	public static Reagent of(Identifier id, int tint) {
		return new Reagent(id, tint);
	}

	@Override
	public String getType() {
		return "reagent";
	}

	@Override
	public boolean consumeSubstrate() {
		return true;
	}
}
