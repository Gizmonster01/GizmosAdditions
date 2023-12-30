package monster.giz.blocks.types;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.item.ItemGroups;

import java.util.*;

public class GABlockSet {
    private Map<GABlockType, Block> blocks;

    public GABlockSet(String name, Block base, EnumSet<GABlockType> types) {
        blocks = GABlockRegistration.registerSet(name, base, types);
    }

    public Block getVariant(GABlockType type) {
        return blocks.getOrDefault(type, null);
    }

    public void addToItemGroup(Block behindBlock) {
        addItemToGroup(behindBlock, GABlockType.WALL);
        addItemToGroup(behindBlock, GABlockType.SLAB);
        addItemToGroup(behindBlock, GABlockType.STAIRS);
        addItemToGroup(behindBlock, GABlockType.BASE);
    }

    public Map<GABlockType, Block> getBlocks() {
        return blocks;
    }

    private void addItemToGroup(Block behindBlock, GABlockType type) {
        Block variantBlock = getVariant(type);
        if (variantBlock != null) {
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
                content.addAfter(behindBlock, variantBlock);
            });
        }
    }

}

