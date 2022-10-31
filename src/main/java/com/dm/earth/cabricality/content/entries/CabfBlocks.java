package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.machine.extractor.ExtractorMachineBlock;
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
    }

    public static void registerFluidBlock(Identifier id, FlowableFluid fluid) {
        Registry.register(Registry.BLOCK, id, new FluidBlock(fluid, QuiltBlockSettings.copy(Blocks.WATER)));
    }

    private static void registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, Cabricality.id(name), block);
        Registry.register(Registry.ITEM, Cabricality.id(name), new BlockItem(block, new QuiltItemSettings().group(Cabricality.MAIN_GROUP)));
    }
}
