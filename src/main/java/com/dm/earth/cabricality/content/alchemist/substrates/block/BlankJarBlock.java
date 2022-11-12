package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;

public class BlankJarBlock extends AbstractJarBlock{
	public BlankJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public AbstractSubstrate getSubstrate() {
		return null;
	}
}
