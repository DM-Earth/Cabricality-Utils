package com.dm.earth.cabricality;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.util.Identifier;

public enum ModEntry {
	MC("minecraft"),
	CR("create"),
	CABF(Cabricality.ID),
	PMD("promenade"),
	IV("indrev"),
	FD("farmersdelight"),
	AP("architects_palette"),
	TC("tconstruct"),
	MUL("malum");

	final String modId;

	ModEntry(String modId) {
		this.modId = modId;
	}

	public Identifier id(String path) {
		return new Identifier(this.modId, path);
	}
}
