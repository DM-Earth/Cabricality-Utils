package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.item.ItemModelGenerator;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfItems {
    public static final Item RESIN_BUCKET = new BucketItem(CabfFluids.RESIN, new QuiltItemSettings().group(Cabricality.MAIN_GROUP).maxCount(1));

    public static void register() {
        registerItemModeled("resin_bucket", RESIN_BUCKET, ItemModelGenerator.generated("item/bucket", "resin_bucket"));
    }

    private static void registerItemModeled(String name, Item item, JModel model) {
        Cabricality.CLIENT_RESOURCES.addModel(model, Cabricality.id("item/" + name));
        registerItem(name, item);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, Cabricality.id(name), item);
    }
}