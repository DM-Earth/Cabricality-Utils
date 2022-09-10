package com.dm.earth.cabricality.mixin;

import com.dm.earth.cabricality.util.VoxelShapeUtil;
import com.github.alexnijjar.ad_astra.blocks.flags.FlagBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlagBlock.class)
public class FlagBlockMixin {

    @Shadow @Final public static DirectionProperty FACING;
    @Shadow @Final public static EnumProperty<DoubleBlockHalf> HALF;

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void injectedOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        Direction direction = state.get(FACING);
        if (state.get(HALF).equals(DoubleBlockHalf.LOWER)) {
            cir.setReturnValue(VoxelShapes.union(
                    VoxelShapeUtil.simpleBox(direction, 0, 0, 7, 5, 16, 9),
                    VoxelShapeUtil.simpleBox(direction, 5, 0, 7.5, 13, 16, 8.5)
            ));
        } else {
            cir.setReturnValue(VoxelShapes.union(
                    VoxelShapeUtil.simpleBox(direction, 0, 0, 7, 5, 14, 9),
                    VoxelShapeUtil.simpleBox(direction, 5, 0, 7.5, 13, 15, 8.5)
            ));
        }
    }
}
