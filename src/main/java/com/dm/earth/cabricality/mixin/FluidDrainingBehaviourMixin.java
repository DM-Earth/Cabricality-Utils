package com.dm.earth.cabricality.mixin;

import com.simibubi.create.content.contraptions.fluids.actors.FluidDrainingBehaviour;
import com.simibubi.create.content.contraptions.fluids.actors.FluidManipulationBehaviour;
import com.simibubi.create.foundation.tileEntity.SmartTileEntity;
import com.simibubi.create.foundation.tileEntity.behaviour.BehaviourType;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidDrainingBehaviour.class)
public class FluidDrainingBehaviourMixin extends FluidManipulationBehaviour {
    boolean infinite;

    public FluidDrainingBehaviourMixin(SmartTileEntity te) {
        super(te);
    }

    @Inject(method = "pullNext", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"), cancellable = true)
    private void injected(BlockPos root, TransactionContext ctx, CallbackInfoReturnable<Boolean> cir) {
        if (this.infinite) cir.setReturnValue(true);
    }

    @Override
    protected SnapshotParticipant<?> snapshotParticipant() {
        return null;
    }

    @Override
    public BehaviourType<?> getType() {
        return null;
    }
}
