package monster.giz.blocks;

import monster.giz.blocks.types.GABlockSet;
import monster.giz.blocks.types.GABlockSetBuilder;
import monster.giz.blocks.types.GABlockType;
import monster.giz.util.GALogger;
import net.minecraft.block.*;

import java.util.EnumSet;

import static monster.giz.blocks.types.GABlockSetTemplates.STONELIKE_BLOCK_SET_TYPES;

public class GizmosAdditionsBlocks {

    public static final GABlockSet MOSSY_COBBLED_DEEPSLATE_SET = new GABlockSet(
            "mossy_cobbled_deepslate",
            Blocks.COBBLED_DEEPSLATE, STONELIKE_BLOCK_SET_TYPES);

    public static final GABlockSet MOSSY_DEEPSLATE_BRICKS_SET = new GABlockSet(
            "mossy_deepslate_bricks",
            Blocks.DEEPSLATE_BRICKS, STONELIKE_BLOCK_SET_TYPES);

    public static void initialize() {
        MOSSY_COBBLED_DEEPSLATE_SET.addToItemGroup(Blocks.COBBLED_DEEPSLATE_WALL);
        MOSSY_DEEPSLATE_BRICKS_SET.addToItemGroup(Blocks.DEEPSLATE_BRICK_WALL);
    }

}
