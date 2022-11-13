package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlankJarBlock extends AbstractJarBlock{
	public BlankJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public AbstractSubstrate getSubstrate() {
		return null;
	}

	@Override
	public @NotNull Identifier getDefaultBlockId() {
		return Cabricality.id("jar");
	}

	@Override
	public @Nullable String getContent() {
		return null;
	}
}
