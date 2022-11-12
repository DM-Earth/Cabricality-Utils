package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.content.alchemist.substrates.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;

public class ReagentJarBlock extends AbstractJarBlock {
	public ReagentJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public AbstractSubstrate getSubstrate() {
		return Reagents.getReagentFromBlock(this);
	}
}
