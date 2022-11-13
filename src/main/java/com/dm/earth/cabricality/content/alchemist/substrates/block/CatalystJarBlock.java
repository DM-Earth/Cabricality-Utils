package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.substrates.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class CatalystJarBlock extends AbstractJarBlock{
	public CatalystJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public AbstractSubstrate getSubstrate() {
		return Reagents.getCatalystFromBlock(this);
	}

	@Override
	public @NotNull Identifier getDefaultBlockId() {
		return Cabricality.id("catalyst_jar");
	}
}
