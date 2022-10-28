package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfItems {
    public static final Item RESIN_BUCKET = new BucketItem(CabfFluids.RESIN, new QuiltItemSettings().group(Cabricality.MAIN_GROUP).recipeRemainder(Items.BUCKET).maxCount(1));

    public static void register() {
        registerItem("resin_bucket", RESIN_BUCKET);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, Cabricality.asIdentifier(name), item);
    }
}
