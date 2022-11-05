package com.dm.earth.cabricality.content.substrates.core;

import com.dm.earth.cabricality.Cabricality;

public class Substrate extends AbstractSubstrate {
	public Substrate(String name, int tint) {
		super(Cabricality.id(name), tint);
	}

	@Override
	public void registerRelatives() {

	}

	@Override
	public String getType() {
		return "substrate";
	}

	@Override
	public boolean consumeSubstrate() {
		return true;
	}
}
