package monster.giz.blocks.types;

import monster.giz.GizmosAdditions;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;

import java.util.*;

public class GABlockSet {
    private Map<GABlockType, Block> blocks;

    private GABlockSet(String namespace, String name, Block base, EnumSet<GABlockType> types) {
        blocks = GABlockRegistration.registerSet(namespace, name, base, types);
    }

    public static GABlockSet newSet(String name, Block base, EnumSet<GABlockType> types) {
        return new GABlockSet(GizmosAdditions.NAMESPACE, name, base, types);
    }

    public static GABlockSet expandVariantsOfExisting(Block base, EnumSet<GABlockType> types) {
        return new GABlockSet(GizmosAdditions.NAMESPACE, Registries.BLOCK.getId(base).toString(),base, types);
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

