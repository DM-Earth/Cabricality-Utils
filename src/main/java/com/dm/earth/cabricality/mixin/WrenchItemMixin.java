package com.dm.earth.cabricality.mixin;

import com.github.alexnijjar.ad_astra.blocks.pipes.Wrenchable;
import com.simibubi.create.content.contraptions.wrench.WrenchItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WrenchItem.class)
public class WrenchItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void injected(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        if (world.getBlockState(context.getBlockPos()).getBlock() instanceof Wrenchable block) {
            block.handleWrench(world, context.getBlockPos(), world.getBlockState(context.getBlockPos()), context.getSide(), context.getPlayer());
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
