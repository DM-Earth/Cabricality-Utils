package com.dm.earth.cabricality.content.trading.core;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.util.Identifier;

import java.util.List;

public record Profession(Identifier id, List<TradingEntry> entries) {

	public static Profession of(String name, TradingEntry... entries) {
		return new Profession(Cabricality.id(name), List.of(entries));
	}

	@Override
	public List<TradingEntry> entries() {
		return List.copyOf(entries);
	}

	@Override
	public int hashCode() {
		return this.id().hashCode();
	}
}
