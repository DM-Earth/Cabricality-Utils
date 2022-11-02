package com.dm.earth.cabricality.content.entries;

import com.dm.earth.cabricality.Cabricality;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class CabfItems {
	public static void register() {
	}

	private static void registerItemModeled(String name, Item item, JModel model) {
		Cabricality.CLIENT_RESOURCES.addModel(model, Cabricality.id("item/" + name));
		registerItem(name, item);
	}

	private static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, Cabricality.id(name), item);
	}

	public static final class Properties {
		public static final Item.Settings DEFAULT = new QuiltItemSettings().group(Cabricality.MAIN_GROUP);
		public static final Item.Settings DEFAULT_SINGLE = DEFAULT.maxCount(1);
	}
}
