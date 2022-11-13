package com.dm.earth.cabricality.content.alchemist.substrates.core;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import net.minecraft.util.Identifier;

public class Reagent extends AbstractSubstrate {
	private Reagent(Identifier id, Identifier item, int tint) {
		super(id, tint);
	}

	public static Reagent of(String id, Identifier item, int tint) {
		return new Reagent(Cabricality.id(id), item, tint);
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
