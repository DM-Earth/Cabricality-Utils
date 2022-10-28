package com.dm.earth.cabricality;

import com.dm.earth.cabricality.content.entries.CabfFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import static com.dm.earth.cabricality.Cabricality.MODID;

public class CabricalityClient implements ClientModInitializer {

	public static String genTranslationKey(String type, String path) {
		return type + "." + MODID + "." + path;
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		ModChecker.check();
		initFluidRendering();
	}

	private static void initFluidRendering() {
		renderFluid("resin", CabfFluids.RESIN, CabfFluids.RESIN_FLOWING);
	}

	private static void renderFluid(String name, Fluid still, Fluid flowing) {
		FluidRenderHandlerRegistry.INSTANCE.register(CabfFluids.RESIN, CabfFluids.RESIN_FLOWING, fluidRenderer(name));
		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), still, flowing);
	}

	private static SimpleFluidRenderHandler fluidRenderer(String name) {
		return new SimpleFluidRenderHandler(Cabricality.asIdentifier(name + "_still"), Cabricality.asIdentifier(name + "_flowing"));
	}
}
