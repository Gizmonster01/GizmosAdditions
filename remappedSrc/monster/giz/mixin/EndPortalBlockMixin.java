package monster.giz.mixin;

import monster.giz.GizmosAdditions;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin {

    // Injected logic at start to check for gamerule, cancel
    @Inject(method = "onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (!world.getGameRules().getBoolean(GizmosAdditions.END_ACCESS)) {
            ci.cancel();
        }
    }
}
