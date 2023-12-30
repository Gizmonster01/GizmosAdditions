package monster.giz.mixin;

import monster.giz.GizmosAdditions;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public abstract class PickUpBlockGoalMixin extends Goal {

    @Shadow @Final
    private EndermanEntity enderman;
    /**
     * @author gizmonster
     * @reason Overrides existing logic on Enderman block pickup to include gamerule check
     */
    @Overwrite
    public boolean canStart() {
        if (this.enderman.getCarriedBlock() != null) {
            return false;
        } else if (!this.enderman.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            return false;
        } else if (!this.enderman.getWorld().getGameRules().getBoolean(GizmosAdditions.ENDERMAN_GRIEFING)) {
            return false;
        } else {
            return this.enderman.getRandom().nextInt(toGoalTicks(20)) == 0;
        }
    }

}