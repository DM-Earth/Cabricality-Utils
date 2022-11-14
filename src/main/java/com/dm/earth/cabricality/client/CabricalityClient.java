package com.dm.earth.cabricality.client;

import com.dm.earth.cabricality.client.listener.ColorRegistryListener;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.trading.util.ProfessionDebugHelper;
import com.dm.earth.cabricality.util.ModChecker;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static com.dm.earth.cabricality.Cabricality.ID;

public class CabricalityClient implements ClientModInitializer {

	public static String genTranslationKey(String type, String path) {
		return type + "." + ID + "." + path;
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		ModChecker.check();
		FluidRendererRegistry.renderFluidInit();
		ColorRegistryListener.load();
		ProfessionDebugHelper.load();

		BlockRenderLayerMap.put(RenderLayer.getCutout(), Reagents.getJarBlocks(true).toArray(new Block[0]));
	}
}
