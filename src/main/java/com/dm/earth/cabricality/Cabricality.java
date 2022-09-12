package com.dm.earth.cabricality;

import com.simibubi.create.AllItems;
import dev.architectury.registry.registries.Registries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cabricality implements ModInitializer {

    public static final String MODID = "cabricality";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(genIdentifier("main_group"))
            .icon(() -> new ItemStack(Blocks.PURPLE_CARPET))
            .build();

    public static final Registries REGISTRIES_CABRICALITY = Registries.get(MODID);

    @Override
    public void onInitialize() {
    }

    public static Identifier genIdentifier(String id) {
        return new Identifier(MODID, id);
    }
}
