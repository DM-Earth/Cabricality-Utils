package com.dm.earth.cabricality.content.trading;

import com.dm.earth.cabricality.content.trading.core.Profession;
import com.dm.earth.cabricality.content.trading.core.TradingEntry;
import com.dm.earth.cabricality.content.trading.core.TradingEntryRegistry;

import static com.dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.GOLD;
import static com.dm.earth.cabricality.content.trading.core.TradingEntry.CoinTypes.SILVER;
import static com.dm.earth.cabricality.content.trading.core.TradingEntry.of;
import static com.dm.earth.cabricality.util.ModEntry.*;

//TODO: fill this out
public enum Professions {
	EXCHANGE(Profession.of("exchange", -1,
			of(SILVER.getId(), 16, GOLD, 1, 0x9fadb4),
			of(GOLD.getId(), 1, SILVER, 16, 0xfdf470)
	)),

	FARMING(Profession.of("farming", 0x7baf4a,
			// Vanilla
			of(MC.id("carrot"), 9, SILVER, 1, 0xfd8e28),
			of(MC.id("beetroot"), 9, SILVER, 1, 0xa2292f),
			of(MC.id("potato"), 9, SILVER, 1, 0xc79643),
			of(MC.id("sweet_berries"), 12, SILVER, 1, 0xa30c0e),
			of(MC.id("cocoa_beans"), 16, SILVER, 1, 0x6f4428),
			of(MC.id("honey_bottle"), 8, SILVER, 1, 0xfd912d),
			of(MC.id("honeycomb"), 4, SILVER, 1, 0xf9be3d),
			of(MC.id("wheat"), 16, SILVER, 1, 0x8c7641),
			of(MC.id("kelp"), 64, SILVER, 1, 0x5ca939),
			of(MC.id("melon_slice"), 64, SILVER, 1, 0xbd322a),
			of(MC.id("white_wool"), 64, SILVER, 1, 0xf8f9f9),
			of(MC.id("cactus"), 16, SILVER, 1, 0x659739),
			// Farmer's Delight
			of(FD.id("rice"), 9, SILVER, 1, 0xe6dfd7),
			of(FD.id("onion"), 9, SILVER, 1, 0xab7437),
			of(FD.id("tomato"), 9, SILVER, 1, 0xbc3427),
			of(FD.id("cabbage"), 9, SILVER, 1, 0x7baf4a),
			of(FD.id("canvas"), 9, SILVER, 1, 0xb69775),
			of(FD.id("pumpkin_slice"), 32, SILVER, 1, 0xde882e)
	)),

	//TODO: terrestria
	CARPENTRY(Profession.of("carpentry", 0x735a38,
			of(MC.id("oak_log"), 32, SILVER, 1, 0x735a38),
			of(MC.id("spruce_log"), 32, SILVER, 1, 0x3a2715),
			of(MC.id("jungle_log"), 32, SILVER, 1, 0x544c1e),
			of(MC.id("dark_oak_log"), 32, SILVER, 1, 0x332716),
			of(MC.id("acacia_log"), 32, SILVER, 1, 0x696259),
			of(MC.id("birch_log"), 32, SILVER, 1, 0xf0eeeb),
			of(MC.id("crimson_stem"), 32, SILVER, 1, 0x871115),
			of(MC.id("warped_stem"), 32, SILVER, 1, 0x1b615b),
			of(PMD.id("palm_log"), 32, SILVER, 1, 0x4d433a),
			of(PMD.id("cherry_oak_log"), 32, SILVER, 1, 0x401c15),
			of(PMD.id("dark_amaranth_stem"), 24, SILVER, 1, 0x4e4053)
	)),

	MINING(Profession.of("mining", 0x826357,
			of(CR.id("crushed_iron_ore"), 3, SILVER, 1, 0xe8c8b2),
			of(CR.id("crushed_copper_ore"), 3, SILVER, 1, 0xe77a57),
			of(CR.id("crushed_zinc_ore"), 3, SILVER, 1, 0xbae8c2),
			of(CR.id("crushed_gold_ore"), 3, SILVER, 2, 0xf9e845),
			of(CR.id("crushed_nickel_ore"), 3, SILVER, 1, 0xe0dcab),
			of(CR.id("crushed_lead_ore"), 3, SILVER, 2, 0x535466),
			of(CR.id("crushed_tin_ore"), 3, SILVER, 1, 0xdcdcdc),
			of(CABF.id("crushed_calorite_ore"), 1, SILVER, 5, 0xb22c45),
			of(CABF.id("crushed_ostrum_ore"), 1, SILVER, 4, 0x644a59),
			of(CABF.id("crushed_desh_ore"), 1, SILVER, 2, 0xdfa562),
			of(CABF.id("crushed_cobalt_ore"), 2, SILVER, 3, 0x2a79da),
			of(MC.id("andesite"), 64, SILVER, 1, 0x868887),
			of(MC.id("granite"), 64, SILVER, 1, 0x9e6b5a),
			of(MC.id("diorite"), 64, SILVER, 1, 0xe6e2e6),
			of(MC.id("tuff"), 48, SILVER, 1, 0x85837b),
			of(MC.id("calcite"), 48, SILVER, 1, 0xedece6),
			of(MC.id("sandstone"), 64, SILVER, 1, 0xdfd3a9),
			of(CR.id("limestone"), 64, SILVER, 1, 0xbbb6a9),
			of(PMD.id("blunite"), 64, SILVER, 1, 0x5f6874),
			of(PMD.id("carbonite"), 64, SILVER, 1, 0x514e52),
			of(CR.id("scoria"), 16, SILVER, 1, 0x493a34),
			of(CR.id("veridium"), 16, SILVER, 1, 0x205f4f),
			of(CR.id("ochrum"), 16, SILVER, 1, 0xb29561),
			of(CR.id("crimsite"), 16, SILVER, 1, 0x7f2f3d),
			of(CR.id("asurine"), 16, SILVER, 1, 0x3f4b68),
			of(IV.id("sulfur_crystal"), 4, SILVER, 1, 0xbbab53),
			of(MC.id("lapis_lazuli"), 6, SILVER, 1, 0x3761c0),
			of(MC.id("diamond"), 1, GOLD, 1, 0x54ecd9),
			of(MC.id("coal"), 16, SILVER, 1, 0x252525),
			of(MC.id("redstone"), 24, SILVER, 1, 0xfc0d1b)
	)),
	MASONRY(Profession.of("masonry", 0xaf6250,
			of(AP.id("basalt_tiles"), 12, SILVER, 1, 0x747474),
			of(AP.id("sunmetal_block"), 8, SILVER, 1, 0x613c3d),
			of(AP.id("osseous_bricks"), 12, SILVER, 1, 0xe8e5d3),
			of(AP.id("packed_ice_bricks"), 12, SILVER, 1, 0x7ea7f1),
			of(AP.id("flint_tiles"), 12, SILVER, 1, 0x302d30),
			of(AP.id("abyssaline_bricks"), 8, SILVER, 1, 0x534265),
			of(AP.id("gilded_sandstone"), 8, SILVER, 1, 0xf8bc39),
			of(AP.id("olivestone_bricks"), 12, SILVER, 1, 0x51523e),
			of(AP.id("algal_bricks"), 12, SILVER, 1, 0x38423b),
			of(AP.id("myonite_bricks"), 12, SILVER, 1, 0x867967),
			// Create
			of(CR.id("ornate_iron_window"), 8, SILVER, 1, 0x7f786f),
			// Tconstruct
			of(TC.id("seared_bricks"), 12, SILVER, 1, 0x3f3c39),
			of(TC.id("scorched_bricks"), 8, SILVER, 1, 0x2d231d),
			of(TC.id("dried_clay_bricks"), 8, SILVER, 1, 0x817157),
			// Vanilla
			of(MC.id("bricks"), 12, SILVER, 1, 0xaf6250),
			of(MC.id("nether_bricks"), 12, SILVER, 1, 0x37181e),
			of(MC.id("quartz_bricks"), 8, SILVER, 1, 0xeeeae6),
			of(MC.id("mossy_cobblestone"), 12, SILVER, 1, 0x738454)
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
