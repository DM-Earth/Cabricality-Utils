package com.dm.earth.cabricality.content.alchemist.substrates.core;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.util.Identifier;

public class Catalyst extends AbstractSubstrate {
	private Catalyst(Identifier id, int tint) {
		super(id, tint);
	}

	public static Catalyst of(String name, int tint) {
		return new Catalyst(Cabricality.id(name), tint);
	}

	@Override
	public String getType() {
		return "catalyst";
	}

	@Override
	public boolean consumeSubstrate() {
		return false;
	}
}
