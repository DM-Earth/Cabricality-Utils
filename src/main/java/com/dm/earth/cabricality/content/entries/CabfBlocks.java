package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.extractor.ExtractorMachineBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfBlocks {
    public static ExtractorMachineBlock EXTRACTOR = new ExtractorMachineBlock(QuiltBlockSettings.of(Material.METAL, MapColor.BROWN));

    public static void register() {
        registerBlock("extractor_machine", EXTRACTOR);
    }

    private static void registerBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, Cabricality.asIdentifier(name), block);
        Registry.register(Registry.ITEM, Cabricality.asIdentifier(name), new BlockItem(block, new QuiltItemSettings().group(Cabricality.MAIN_GROUP)));
    }
}
