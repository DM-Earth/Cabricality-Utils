package com.dm.earth.cabricality;

import com.dm.earth.cabricality.util.DataFixerListener;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cabricality implements ModInitializer {

    public static final String MODID = "cabricality";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(asIdentifier("main_group"), () -> Registry.ITEM.get(asIdentifier("andesite_machine")).getDefaultStack());

    public static Identifier asIdentifier(String id) {
        return new Identifier(MODID, id);
    }

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Cabricality is initializing!");
        DataFixerListener.load();
    }
}
