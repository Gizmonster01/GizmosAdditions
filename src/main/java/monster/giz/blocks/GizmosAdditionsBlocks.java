package monster.giz.blocks;

import monster.giz.GizmosAdditions;
import monster.giz.blocks.types.*;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;

import java.util.EnumSet;

import static monster.giz.blocks.types.GABlockSetTemplates.STONELIKE_BLOCK_SET_TYPES;

public class GizmosAdditionsBlocks {

    public static final GABlockSet MOSSY_COBBLED_DEEPSLATE_SET = GABlockSet.newSet(
            "mossy_cobbled_deepslate",
            Blocks.COBBLED_DEEPSLATE, STONELIKE_BLOCK_SET_TYPES);

    public static final GABlockSet MOSSY_DEEPSLATE_BRICKS_SET = GABlockSet.newSet(
            "mossy_deepslate_bricks",
            Blocks.DEEPSLATE_BRICKS, STONELIKE_BLOCK_SET_TYPES);

    public static final GABlockSet POLISHED_AMETHYST_SET = GABlockSet.newSet(
            "polished_amethyst",
            Blocks.AMETHYST_BLOCK, STONELIKE_BLOCK_SET_TYPES);

    public static void initialize() {
        MOSSY_COBBLED_DEEPSLATE_SET.addToItemGroup(Blocks.COBBLED_DEEPSLATE_WALL);
        MOSSY_DEEPSLATE_BRICKS_SET.addToItemGroup(Blocks.DEEPSLATE_BRICK_WALL);
        POLISHED_AMETHYST_SET.addToItemGroup(Blocks.AMETHYST_BLOCK);
    }

}
