package com.dm.earth.cabricality.client;

import com.dm.earth.cabricality.Cabricality;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class FluidRendererRegistry {
    public static void register(String name, String texture, Fluid still, Fluid flowing, boolean flow) {
        int color = FluidColorRegistry.get(name);
        Identifier stillId = Cabricality.id("fluid/" + texture + "/" + texture + "_still");
        Identifier flowingId = Cabricality.id("fluid/" + texture + "/" + texture + "_flowing");
        SimpleFluidRenderHandler handler;
        if (color < 0) handler = new SimpleFluidRenderHandler(stillId, flowingId);
        else handler = new SimpleFluidRenderHandler(stillId, flowingId, color);
        FluidRenderHandlerRegistry.INSTANCE.register(still, flowing, handler);
        if (flow) BlockRenderLayerMap.put(RenderLayer.getTranslucent(), still, flowing);
        else BlockRenderLayerMap.put(RenderLayer.getTranslucent(), still);
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillId);
            registry.register(flowingId);
        });
    }

    public static void register(String name, Fluid still, Fluid flowing, boolean flow) {
        register(name, name, still, flowing, flow);
    }
}
