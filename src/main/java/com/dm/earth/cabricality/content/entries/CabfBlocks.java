package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.ResourcedBlock;
import com.dm.earth.cabricality.content.alchemist.substrates.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrates.block.ReagentJarBlock;
import com.dm.earth.cabricality.content.alchemist.substrates.core.Reagent;
import com.dm.earth.cabricality.content.machine.extractor.ExtractorMachineBlock;
import net.devtech.arrp.generator.BlockResourceGenerator;
import net.devtech.arrp.json.loot.JLootTable;
import net.minecraft.block.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfBlocks {
	public static ExtractorMachineBlock EXTRACTOR = new ExtractorMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.BROWN));

	public static void register() {
		registerBlock("extractor_machine", EXTRACTOR);

		// Substrate Jars
		for (Reagents reagents : Reagents.values()) {
			registerBlock("catalyst_jar_" + reagents.getCatalyst().hashString(), new ReagentJarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.GRAY)));
			for (Reagent reagent : reagents.getReagents()) {
				registerBlock("reagent_jar_" + reagent.hashString(), new ReagentJarBlock(QuiltBlockSettings.of(Material.GLASS, MapColor.SPRUCE_BROWN)));
			}
		}
	}

	public static void registerFluidBlock(Identifier id, FlowableFluid fluid) {
		Registry.register(Registry.BLOCK, id, new FluidBlock(fluid, QuiltBlockSettings.copy(Blocks.WATER)));
	}

	private static void registerBlock(String name, Block block) {
		if (block instanceof ResourcedBlock resourced) {
			if (resourced.doModel()) resourced.writeBlockModel(Cabricality.CLIENT_RESOURCES);
			if (resourced.doLootTable()) resourced.writeLootTable(Cabricality.SERVER_RESOURCES);
			if (resourced.doBlockStates()) resourced.writeBlockStates(Cabricality.CLIENT_RESOURCES);
			if (resourced.doItemModel()) resourced.writeItemModel(Cabricality.CLIENT_RESOURCES);
		}
		Registry.register(Registry.BLOCK, Cabricality.id(name), block);
		Registry.register(Registry.ITEM, Cabricality.id(name), new BlockItem(block, new QuiltItemSettings().group(Cabricality.MAIN_GROUP)));
	}
}
