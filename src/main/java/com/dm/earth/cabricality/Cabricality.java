package com.dm.earth.cabricality;

import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.entries.CabfBlockEntityTypes;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.trading.data.recipe.Trading;
import com.dm.earth.cabricality.listener.DataFixerListener;
import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.dm.earth.cabricality.listener.UseEntityListener;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cabricality implements ModInitializer {

	public static final String ID = "cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final RuntimeResourcePack CLIENT_RESOURCES = RuntimeResourcePack.create(id("client_resources"));
	public static final RuntimeResourcePack SERVER_RESOURCES = RuntimeResourcePack.create(id("server_resources"));
	public static ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("main"), () -> Registry.ITEM.get(Cabricality.id("andesite_machine")).getDefaultStack());
	public static ItemGroup SUBSTRATES_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("substrates"), () -> Registry.ITEM.get(Cabricality.id("jar")).getDefaultStack());

	@Contract("_ -> new")
	public static @NotNull Identifier id(String id) {
		return new Identifier(ID, id);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Cabricality is initializing! 📦");

		Trading.load();
		Alchemist.load();
		DeployerCuttingRecipeHandler.load();

		CabfItems.register();
		CabfBlocks.register();
		CabfFluids.register();
		CabfBlockEntityTypes.register();

		DataFixerListener.load();
		UseEntityListener.load();

		initClientAssets();
		RRPCallback.AFTER_VANILLA.register(list -> list.add(SERVER_RESOURCES));
	}

	@ClientOnly
	private static void initClientAssets() {
		RRPCallback.AFTER_VANILLA.register(list -> list.add(CLIENT_RESOURCES));
	}
}
