package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.Cabricality;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class BaseFluid extends Fluid implements IFluid {
    private final String name;
    private int tint;

    public BaseFluid(String name) {
        this.name = name;
        this.tint = -1;
    }

    public BaseFluid color(int tintInt) {
        this.tint = tintInt;
        return this;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return true;
    }

    @Override
    protected Vec3d getVelocity(BlockView world, BlockPos pos, FluidState state) {
        return Vec3d.ZERO;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 0;
    }

    @Override
    protected float getBlastResistance() {
        return 0;
    }

    @Override
    public float getHeight(FluidState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public float getHeight(FluidState state) {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean isStill(FluidState state) {
        return true;
    }

    @Override
    public int getLevel(FluidState state) {
        return 8;
    }

    @Override
    public VoxelShape getShape(FluidState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public Identifier getRegistryName() {
        return this.getId();
    }

    @Override
    public Identifier getId() {
        return Cabricality.id(this.getName());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Fluid getFlowing() {
        return this.getTypical();
    }

    @Override
    public void setupRendering() {
        Identifier stillId = Cabricality.id("fluid/" + this.getName() + "/" + this.getName() + "_still");
        Identifier flowingId = Cabricality.id("fluid/" + this.getName() + "/" + this.getName() + "_flowing");
        SimpleFluidRenderHandler handler;
        if (this.tint < 0) handler = new SimpleFluidRenderHandler(stillId, flowingId); else handler = new SimpleFluidRenderHandler(stillId, flowingId, tint);
        FluidRenderHandlerRegistry.INSTANCE.register(this.getTypical(), this.getFlowing(), handler);
        BlockRenderLayerMap.put(RenderLayer.getTranslucent(), this.getTypical());
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillId);
            registry.register(flowingId);
        });
    }

    @Override
    public Fluid getTypical() {
        return this;
    }

    @Override
    public Item getBucketItem() {
        Identifier id = Cabricality.id(this.getName() + "_bucket");
        if (Registry.ITEM.containsId(id)) return Registry.ITEM.get(id);
        return Items.AIR;
    }
}
