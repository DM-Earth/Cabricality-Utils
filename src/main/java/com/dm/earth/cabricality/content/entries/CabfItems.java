package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.gen.item.ItemModelGenerator;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfItems {
    public static final BucketItem RESIN_BUCKET = new BucketItem(CabfFluids.RESIN, Properties.DEFAULT_ONE);
    public static final BucketItem REDSTONE_BUCKET = new BucketItem(CabfFluids.REDSTONE, Properties.DEFAULT_ONE);
    public static final BucketItem WASTE_BUCKET = new BucketItem(CabfFluids.WASTE, Properties.DEFAULT_ONE);
    public static final BucketItem SKY_STONE_BUCKET = new BucketItem(CabfFluids.SKY_STONE, Properties.DEFAULT_ONE);

    public static void register() {
        registerItemModeled("resin_bucket", RESIN_BUCKET, ItemModelGenerator.generated("item/bucket", "resin_bucket"));
        registerItemModeled("redstone_bucket", REDSTONE_BUCKET, ItemModelGenerator.generated("item/bucket", "redstone_bucket"));
        registerItemModeled("waste_bucket", WASTE_BUCKET, ItemModelGenerator.generated("item/bucket", "waste_bucket"));
        registerItemModeled("sky_stone_bucket", SKY_STONE_BUCKET, ItemModelGenerator.generated("item/bucket", "sky_stone_bucket"));
    }

    private static void registerItemModeled(String name, Item item, JModel model) {
        Cabricality.CLIENT_RESOURCES.addModel(model, Cabricality.id("item/" + name));
        registerItem(name, item);
    }

    private static void registerItem(String name, Item item) {
        Registry.register(Registry.ITEM, Cabricality.id(name), item);
    }

    private static final class Properties {
        public static final Item.Settings DEFAULT = new QuiltItemSettings().group(Cabricality.MAIN_GROUP);
        public static final Item.Settings DEFAULT_ONE = DEFAULT.maxCount(1);
    }
}
