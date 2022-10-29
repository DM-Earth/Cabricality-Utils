package com.dm.earth.cabricality;

import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.util.ModChecker;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

import static com.dm.earth.cabricality.Cabricality.ID;

public class CabricalityClient implements ClientModInitializer {

    public static String genTranslationKey(String type, String path) {
        return type + "." + ID + "." + path;
    }

    @Override
    public void onInitializeClient(ModContainer mod) {
        ModChecker.check();
        initFluidRendering();
    }

    private static void initFluidRendering() {
        renderFluid("resin", CabfFluids.RESIN);
    }

    private static void renderFluid(String name, Fluid still, Fluid flowing) {
        Identifier stillId = Cabricality.id("fluid/" + name + "_still");
        Identifier flowingId = Cabricality.id("fluid/" + name + "_flowing");
        FluidRenderHandlerRegistry.INSTANCE.register(still, flowing, new SimpleFluidRenderHandler(stillId, flowingId));
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), still, flowing);
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillId);
            registry.register(flowingId);
        });
    }

    private static void renderFluid(String name, Fluid fluid) {
        Identifier stillId = Cabricality.id("fluid/" + name + "_still");
        Identifier flowingId = Cabricality.id("fluid/" + name + "_flowing");
        FluidRenderHandlerRegistry.INSTANCE.register(fluid, new SimpleFluidRenderHandler(stillId, flowingId));
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), fluid);
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillId);
            registry.register(flowingId);
        });
    }
}
