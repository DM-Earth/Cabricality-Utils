package com.dm.earth.cabricality.content.trading.util;

import com.dm.earth.cabricality.content.trading.Professions;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.item.ProfessionCardItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ProfessionUtil {
	@Nullable
	public static Profession fromItem(ProfessionCardItem item) {
		Optional<Professions> professions = Arrays.stream(Professions.values()).filter(p -> Objects.equals(p.get().hashString(), getHashString(Registry.ITEM.getId(item)))).findFirst();
		return professions.map(Professions::get).orElse(null);
	}

	private static String getHashString(Identifier id) {
		return id.getPath().replaceAll("profession_card_", "");
	}
}
