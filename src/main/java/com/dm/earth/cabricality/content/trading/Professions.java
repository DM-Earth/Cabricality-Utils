package com.dm.earth.cabricality.content.trading;

import com.dm.earth.cabricality.content.trading.core.Profession;
import net.minecraft.util.Identifier;

import static com.dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.SILVER;
import static com.dm.earth.cabricality.content.trading.core.TradingEntry.of;

public enum Professions {
	FARMING(Profession.of("farming",
			// Vanilla
			of(new Identifier("minecraft", "carrot"), 9, SILVER, 1, 0xfd8e28),
			of(new Identifier("minecraft", "beetroot"), 9, SILVER, 1, 0xa2292f),
			of(new Identifier("minecraft", "potato"), 9, SILVER, 1, 0xc79643),
			of(new Identifier("minecraft", "sweet_berries"), 12, SILVER, 1, 0xa30c0e),
			of(new Identifier("minecraft", "cocoa_beans"), 16, SILVER, 1, 0x6f4428),
			of(new Identifier("minecraft", "honey_bottle"), 8, SILVER, 1, 0xfd912d),
			of(new Identifier("minecraft", "honeycomb"), 4, SILVER, 1, 0xf9be3d),
			of(new Identifier("minecraft", "wheat"), 16, SILVER, 1, 0x8c7641),
			of(new Identifier("minecraft", "kelp"), 64, SILVER, 1, 0x5ca939),
			of(new Identifier("minecraft", "melon_slice"), 64, SILVER, 1, 0xbd322a),
			of(new Identifier("minecraft", "wool"), 64, SILVER, 1, 0xf8f9f9),
			// Farmer's Delight
			of(new Identifier("farmersdelight", "rice"), 9, SILVER, 1, 0xe6dfd7),
			of(new Identifier("farmersdelight", "onion"), 9, SILVER, 1, 0xab7437),
			of(new Identifier("farmersdelight", "tomato"), 9, SILVER, 1, 0xbc3427),
			of(new Identifier("farmersdelight", "cabbage"), 9, SILVER, 1, 0x7baf4a),
			of(new Identifier("farmersdelight", "canvas"), 9, SILVER, 1, 0xb69775),
			of(new Identifier("farmersdelight", "pumpkin_slice"), 32, SILVER, 1, 0xde882e)
	)),

	//TODO: terrestria
	CARPENTRY(Profession.of("carpentry",
			of(new Identifier("minecraft", "oak_log"), 32, SILVER, 1, 0x735a38),
			of(new Identifier("minecraft", "spruce_log"), 32, SILVER, 1, 0x3a2715),
			of(new Identifier("minecraft", "jungle_log"), 32, SILVER, 1, 0x544c1e),
			of(new Identifier("minecraft", "dark_oak_log"), 32, SILVER, 1, 0x332716),
			of(new Identifier("minecraft", "acacia_log"), 32, SILVER, 1, 0x696259),
			of(new Identifier("minecraft", "birch_log"), 32, SILVER, 1, 0xf0eeeb),
			of(new Identifier("minecraft", "crimson_stem"), 32, SILVER, 1, 0x871115),
			of(new Identifier("minecraft", "warped_stem"), 32, SILVER, 1, 0x1b615b),
			of(new Identifier("promenade", "palm_log"), 32, SILVER, 1, 0x4d433a),
			of(new Identifier("promenade", "cherry_oak_log"), 32, SILVER, 1, 0x401c15),
			of(new Identifier("promenade", "dark_amaranth_stem"), 32, SILVER, 1, 0x4e4053)
	));

	private final Profession profession;

	Professions(Profession profession) {
		this.profession = profession;
	}

	public Profession get() {
		return profession;
	}
}
