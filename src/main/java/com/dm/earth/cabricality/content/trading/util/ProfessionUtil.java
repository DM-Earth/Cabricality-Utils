package com.dm.earth.cabricality.content.trading.util;

import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

public class ProfessionUtil {
	@Nullable
	public static Profession fromItem(ProfessionCardItem item) {
		Optional<Professions> professions = Arrays.stream(Professions.values()).filter(p -> p.get().hashCode() == TradingEntryRegistry.fromItem(item).hashCode()).findFirst();
		return professions.map(Professions::get).orElse(null);
	}
}
