package com.dm.earth.cabricality.content.fluids;

import com.dm.earth.cabricality.Cabricality;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public abstract class BaseFlowableFluid extends FlowableFluid implements IFluid {
    private final boolean isStill;
    private final BucketItem bucketItem;

    private final String name;

    public BaseFlowableFluid(String name, boolean isStill, BucketItem bucketItem) {
        this.isStill = isStill;
        this.bucketItem = bucketItem;
        this.name = name;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return Registry.BLOCK.get(this.getId()).getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public Identifier getRegistryName() {
        return this.getId();
    }

    @Override
    public Fluid getTypical() {
        return this.getStill();
    }

    @Override
    public Identifier getId() {
        return Cabricality.id(this.getName());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setupRendering() {
        if (this.isStill(null)) {
            Identifier stillId = Cabricality.id("fluid/" + this.getName() + "_still");
            Identifier flowingId = Cabricality.id("fluid/" + this.getName() + "_flowing");
            FluidRenderHandlerRegistry.INSTANCE.register(this.getStill(), this.getFlowing(), new SimpleFluidRenderHandler(stillId, flowingId));
            BlockRenderLayerMap.put(RenderLayer.getTranslucent(), this.getStill(), this.getFlowing());
            ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
                registry.register(stillId);
                registry.register(flowingId);
            });
        }
    }

    @Override
    public boolean isStill(FluidState state) {
        return this.isStill;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    public Item getBucketItem() {
        return this.bucketItem;
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
        super.appendProperties(builder);
        if (!this.isStill(null)) builder.add(LEVEL);
    }

    @Override
    public int getLevel(FluidState state) {
        if (!this.isStill(null)) return 8;
        return state.get(LEVEL);
    }
}
