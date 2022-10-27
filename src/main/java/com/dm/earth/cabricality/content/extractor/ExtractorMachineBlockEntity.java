package com.dm.earth.cabricality.content.extractor;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.util.TransferUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.PlayerLookup;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import java.util.Arrays;

import static com.dm.earth.cabricality.util.CabfDebugger.debug;

@SuppressWarnings("UnstableApiUsage")
public class ExtractorMachineBlockEntity extends BlockEntity {
    public static final BlockEntityType<ExtractorMachineBlockEntity> TYPE = QuiltBlockEntityTypeBuilder.create(ExtractorMachineBlockEntity::new, CabfBlocks.EXTRACTOR).build();

    public final SingleVariantStorage<FluidVariant> storage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FluidConstants.BOTTLE * 4;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            assert world != null;
            if (!world.isClient()) {
                PacketByteBuf buf = PacketByteBufs.create();
                PlayerLookup.tracking(ExtractorMachineBlockEntity.this).forEach(player -> ServerPlayNetworking.send(player, Cabricality.asIdentifier("extractor_buf"), buf));
            }
        }
    };

    public ExtractorMachineBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TYPE, blockPos, blockState);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        storage.variant = FluidVariant.fromNbt(nbt.getCompound("fluid"));
        storage.amount = nbt.getLong("amount");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("fluid", storage.variant.toNbt());
        nbt.putLong("amount", storage.amount);
    }

    public void tick() {
        debug("randomTick from extractor block entity at " + pos.toShortString() + " capacity: " + storage.getCapacity());
        if (isNextToTree() && storage.amount < storage.getCapacity()) {
            debug("extractor block entity: inserting to storage");
            storage.insert(FluidVariant.of(Registry.FLUID.get(Cabricality.asIdentifier("resin"))), FluidConstants.BOTTLE, TransferUtil.getTransaction());
        }
    }

    private boolean isNextToTree() {
        assert world != null;
        for (Direction direction : Arrays.stream(Direction.values()).filter((direction -> direction != Direction.UP && direction != Direction.DOWN)).toArray(Direction[]::new)) {
            BlockState targetState = world.getBlockState(pos.offset(direction));
            if (isVecLog(targetState)) {
                debug("extractor block entity: found log at " + pos.offset(direction).toShortString());
                //check if there are enough logs
                boolean enoughLogs = false;
                BlockPos targetPos = pos.offset(direction);
                int i = 0;
                BlockPos upPos;
                BlockPos downPos;
                while (true) {
                    if (isVecLog(world.getBlockState(targetPos.offset(Direction.UP, i)))) i++;
                    else {
                        upPos = targetPos.offset(Direction.UP, i);
                        break;
                    }
                    if (i >= 4) {
                        upPos = targetPos.offset(Direction.UP, i);
                        enoughLogs = true;
                    }
                }
                while (true) {
                    if (isVecLog(world.getBlockState(targetPos.offset(Direction.DOWN, i)))) i++;
                    else {
                        downPos = targetPos.offset(Direction.DOWN, i);
                        break;
                    }
                    if (i >= 4) {
                        downPos = targetPos.offset(Direction.DOWN, i);
                        enoughLogs = true;
                    }
                }
                if (enoughLogs) {
                    debug("extractor block entity: found enough logs at " + targetPos.toShortString());
                    //check if there are leaves
                    boolean enoughLeaves = true;
                    for (Direction leafDirection : Arrays.stream(Direction.values()).filter((leafDirection -> leafDirection != Direction.DOWN)).toArray(Direction[]::new)) {
                        BlockState targetLeafState = world.getBlockState(upPos.offset(leafDirection));
                        if (!(targetLeafState.getBlock() instanceof LeavesBlock)) enoughLeaves = false;
                    }
                    if (enoughLeaves) debug("extractor block entity: found enough leaves at " + upPos.toShortString());
                    return enoughLeaves;
                } else return false;
            }
        }
        return false;
    }

    //TODO: check tag working?
    private static boolean isVecLog(BlockState blockState) {
        return blockState.getBlock() instanceof PillarBlock block && Registry.BLOCK.getTag(BlockTags.LOGS).get().stream().anyMatch(blockHolder -> blockHolder.value() == block) && blockState.get(PillarBlock.AXIS) == Direction.Axis.Y;
    }
}
