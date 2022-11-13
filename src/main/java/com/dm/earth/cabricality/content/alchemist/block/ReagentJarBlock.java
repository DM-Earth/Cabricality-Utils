package com.dm.earth.cabricality.content.alchemist.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrate.Substrate;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ReagentJarBlock extends SubstrateJarBlock {
	public ReagentJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getReagentFromBlock(this);
	}

	@Override
	public @NotNull Identifier getDefaultBlockId() {
		return Cabricality.id("reagent_jar");
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block/jar/reagent");
	}
}
