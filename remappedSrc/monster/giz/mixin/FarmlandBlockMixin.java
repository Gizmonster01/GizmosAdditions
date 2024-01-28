package monster.giz.mixin;

import monster.giz.GizmosAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.block.FarmlandBlock.setToDirt;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin extends Block {

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author gizmonster
     * @reason Overwrites existing logic to include a check for the gamerule and to see if the entity is wearing leather boots
     */
    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (world.isClient) {
            return;
        }
        if (!(world.random.nextFloat() < fallDistance - 0.5F)) {
            return;
        }
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        if (!(entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))) {
            return;
        }
        if (!world.getGameRules().getBoolean(GizmosAdditions.LEATHER_TRAMPLE)) {
            if (!(((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isEmpty())) {
                if (((LivingEntity) entity).getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS)) {
                    return;
                }
            }
        }
        if (!(entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F)) {
            return;
        }
        setToDirt(entity, state, world, pos);
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }
}
