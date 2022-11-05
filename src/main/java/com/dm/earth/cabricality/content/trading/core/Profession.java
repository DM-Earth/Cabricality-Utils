package com.dm.earth.cabricality.content.trading.core;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.util.Identifier;

import java.util.List;

public class Profession {
	private final Identifier id;
	private final List<TradingEntry> entries;

	public Profession(Identifier id, List<TradingEntry> entries) {
		this.id = id;
		this.entries = entries;
	}

	public static Profession of(String name, TradingEntry... entries) {
		return new Profession(Cabricality.id(name), List.of(entries));
	}

	public Identifier getId() {
		return id;
	}

	public List<TradingEntry> getEntries() {
		return List.copyOf(entries);
	}
}
