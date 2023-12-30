package monster.giz.blocks.types;

import java.util.EnumSet;

public class GABlockSetTemplates {

    // Slab, Stair, Wall
    public static final EnumSet<GABlockType> STONELIKE_BLOCK_SET_TYPES = new GABlockSetBuilder()
            .addVariant(GABlockType.SLAB)
            .addVariant(GABlockType.STAIRS)
            .addVariant(GABlockType.WALL)
            .build();

    public static final EnumSet<GABlockType> BLOCK_STAIR_SLAB = new GABlockSetBuilder()
            .addVariant(GABlockType.SLAB)
            .addVariant(GABlockType.STAIRS)
            .build();
}
