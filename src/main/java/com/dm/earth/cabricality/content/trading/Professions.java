package com.dm.earth.cabricality.content.trading;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;
import net.minecraft.util.Identifier;

import static com.dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.GOLD;
import static com.dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.SILVER;
import static com.dm.earth.cabricality.content.trading.core.TradingEntry.of;

public enum Professions {
	EXCHANGE(Profession.of("exchange", -1,
			of(SILVER.getId(), 16, GOLD, 1, 0x9fadb4),
			of(GOLD.getId(), 1, SILVER, 16, 0xfdf470)
	)),

	FARMING(Profession.of("farming", 0x7baf4a,
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
			of(new Identifier("minecraft", "white_wool"), 64, SILVER, 1, 0xf8f9f9),
			// Farmer's Delight
			of(new Identifier("farmersdelight", "rice"), 9, SILVER, 1, 0xe6dfd7),
			of(new Identifier("farmersdelight", "onion"), 9, SILVER, 1, 0xab7437),
			of(new Identifier("farmersdelight", "tomato"), 9, SILVER, 1, 0xbc3427),
			of(new Identifier("farmersdelight", "cabbage"), 9, SILVER, 1, 0x7baf4a),
			of(new Identifier("farmersdelight", "canvas"), 9, SILVER, 1, 0xb69775),
			of(new Identifier("farmersdelight", "pumpkin_slice"), 32, SILVER, 1, 0xde882e)
	)),

	//TODO: terrestria
	CARPENTRY(Profession.of("carpentry", 0x735a38,
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
			of(new Identifier("promenade", "dark_amaranth_stem"), 24, SILVER, 1, 0x4e4053)
	)),

	MINING(Profession.of("mining", 0x826357,
			of(new Identifier("create", "crushed_iron_ore"), 3, SILVER, 1, 0xe8c8b2),
			of(new Identifier("create", "crushed_copper_ore"), 3, SILVER, 1, 0xe77a57),
			of(new Identifier("create", "crushed_zinc_ore"), 3, SILVER, 1, 0xbae8c2),
			of(new Identifier("create", "crushed_gold_ore"), 3, SILVER, 2, 0xf9e845),
			of(new Identifier("create", "crushed_nickel_ore"), 3, SILVER, 1, 0xe0dcab),
			of(new Identifier("create", "crushed_lead_ore"), 3, SILVER, 2, 0x535466),
			of(new Identifier("create", "crushed_tin_ore"), 3, SILVER, 1, 0xdcdcdc),
			of(Cabricality.id("crushed_calorite_ore"), 1, SILVER, 5, 0xb22c45),
			of(Cabricality.id("crushed_ostrum_ore"), 1, SILVER, 4, 0x644a59),
			of(Cabricality.id("crushed_desh_ore"), 1, SILVER, 2, 0xdfa562),
			of(Cabricality.id("crushed_cobalt_ore"), 2, SILVER, 3, 0x2a79da),
			of(new Identifier("minecraft", "andesite"), 64, SILVER, 1, 0x868887),
			of(new Identifier("minecraft", "granite"), 64, SILVER, 1, 0x9e6b5a),
			of(new Identifier("minecraft", "diorite"), 64, SILVER, 1, 0xe6e2e6),
			of(new Identifier("minecraft", "tuff"), 48, SILVER, 1, 0x85837b),
			of(new Identifier("minecraft", "sandstone"), 64, SILVER, 1, 0xdfd3a9),
			of(new Identifier("create", "limestone"), 64, SILVER, 1, 0xbbb6a9),
			of(new Identifier("promenade", "blunite"), 64, SILVER, 1, 0x5f6874),
			of(new Identifier("promenade", "carbonite"), 64, SILVER, 1, 0x514e52),
			of(new Identifier("create", "scoria"), 16, SILVER, 1, 0x493a34),
			of(new Identifier("create", "veridium"), 16, SILVER, 1, 0x205f4f),
			of(new Identifier("create", "ochrum"), 16, SILVER, 1, 0xb29561),
			of(new Identifier("create", "crimsite"), 16, SILVER, 1, 0x7f2f3d),
			of(new Identifier("create", "asurine"), 16, SILVER, 1, 0x3f4b68),
			of(new Identifier("indrev", "sulfur_crystal"), 4, SILVER, 1, 0xbbab53),
			of(new Identifier("minecraft", "lapis_lazuli"), 6, SILVER, 1, 0x3761c0),
			of(new Identifier("minecraft", "diamond"), 1, GOLD, 1, 0x54ecd9),
			of(new Identifier("minecraft", "coal"), 16, SILVER, 1, 0x252525),
			of(new Identifier("minecraft", "redstone"), 24, SILVER, 1, 0xfc0d1b)
	));

	private final Profession profession;

	Professions(Profession profession) {
		this.profession = profession;
		for (TradingEntry entry : profession.entries()) TradingEntryRegistry.register(entry);
	}

	public Profession get() {
		return profession;
	}

	public static void load() {
		// Load the enum
	}
}
