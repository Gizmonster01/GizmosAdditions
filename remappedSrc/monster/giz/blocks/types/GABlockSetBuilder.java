package monster.giz.blocks.types;

import net.minecraft.block.Block;

import java.util.EnumSet;

public class GABlockSetBuilder {
    private final EnumSet<GABlockType> variants = EnumSet.noneOf(GABlockType.class);

    public GABlockSetBuilder addVariant(GABlockType variant) {
        variants.add(variant);
        return this;
    }

    public EnumSet<GABlockType> build() {
        return variants;
    }
}

