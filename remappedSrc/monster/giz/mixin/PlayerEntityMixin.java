package monster.giz.mixin;

import monster.giz.GizmosAdditions;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow @Nullable public abstract ItemEntity dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership);

    double xpFromLevel(int level) {
        return (level <= 16)
                ? level * level + 6 * level
                : (level <= 32)
                ? (2.5 * level * level - 40.5 * level + 360)
                : (4.5 * level * level - 162.5 * level + 2220);
    }

    @Inject(at = @At("HEAD"), method = "getXpToDrop()I", cancellable = true)
    public void getXpToDrop(CallbackInfoReturnable<Integer> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        int xpPercent = player.method_48926().getGameRules().getInt(GizmosAdditions.XP_DROP_PERCENTAGE);
        if (xpPercent == -1) {
            return;
        }

        if (player.method_48926().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || player.isSpectator()) {
            cir.setReturnValue(0);
        }

        double factor = (double) xpPercent / 100;

        int xp = (int) (factor * (xpFromLevel(player.experienceLevel)));

        cir.setReturnValue(xp);
    }



}
