package com.dm.earth.cabricality.content.trading.core;

import com.dm.earth.cabricality.content.trading.item.AbstractTradeCardItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class TradingEntryRegistry {
	public static ArrayList<TradingEntry> entries = new ArrayList<>();

	public static TradingEntry register(TradingEntry entry) {
		entries.add(entry);
		return entry;
	}

	public static TradingEntry get(Identifier id) {
		for (TradingEntry entry : entries) if (entry.getId().equals(id)) return entry;
		return null;
	}

	public static List<TradingEntry> getEntries() {
		return entries;
	}

	public static TradingEntry fromHashCode(int hash) {
		for (TradingEntry entry : entries) if (entry.hashCode() == hash) return entry;
		return null;
	}

	public static TradingEntry fromItem(AbstractTradeCardItem item) {
		Identifier id = Registry.ITEM.getId(item);
		return fromHashCode(Integer.parseInt(id.getPath().replaceAll(item.getType() + "_", "")));
	}
}
