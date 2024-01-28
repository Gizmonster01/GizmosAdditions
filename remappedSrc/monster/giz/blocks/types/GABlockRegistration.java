package monster.giz.blocks.types;

import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import static monster.giz.GizmosAdditions.NAMESPACE;

public class GABlockRegistration {

    public static String fixName(String id) {
        return id.replace("bricks", "brick");
    }

    public static Map<GABlockType, Block> registerSet(String namespace, String name, Block vanillaBaseBlock, EnumSet<GABlockType> types) {
        GALogger.log("Attempting to register blockset: " + name);
        Map<GABlockType, Block> blockVariants = new EnumMap<>(GABlockType.class);
        Block newBaseBlock = registerBlock(namespace, name, new Block(FabricBlockSettings.copyOf(vanillaBaseBlock)));
        blockVariants.put(GABlockType.BASE, newBaseBlock);

        types.forEach(type -> blockVariants.put(type, createVariant(namespace, name, newBaseBlock, type)));
        return blockVariants;
    }

    public static Block registerBlock(String namespace, String name, Block base) {
        GALogger.log("Attempting to register: " + name);
        Block block = Registry.register(Registries.BLOCK, new Identifier(namespace, name), base);
        Registry.register(Registries.ITEM, new Identifier(namespace, name), new BlockItem(block, new FabricItemSettings()));
        return block;
    }

    public static Block registerBlock(String namespace, String name, AbstractBlock.Settings settings) {
        GALogger.log("Attempting to register: " + name);
        Block block = Registry.register(Registries.BLOCK, new Identifier(namespace, name), new Block(settings));
        Registry.register(Registries.ITEM, new Identifier(namespace, name), new BlockItem(block, new FabricItemSettings()));
        return block;
    }

    private static Block createVariant(String namespace, String name, Block base, GABlockType variantType) {
        String variantName = fixName(name) + "_" + variantType.getName();
        switch (variantType) {
            case SLAB:
                return registerBlock(namespace, variantName, new SlabBlock(FabricBlockSettings.copy(base)));
            case STAIRS:
                return registerBlock(namespace, variantName, new StairsBlock(base.getDefaultState(), FabricBlockSettings.copy(base)));
            case WALL:
                return registerBlock(namespace, variantName, new WallBlock(FabricBlockSettings.copy(base)));
            case FENCE:
                return registerBlock(namespace, variantName, new FenceBlock(FabricBlockSettings.copy(base)));
                /*
            case FENCE_GATE:
                return registerBlock(variantName, new FenceGateBlock(FabricBlockSettings.copy(base)));
                 */
            default:
                throw new IllegalArgumentException("Unsupported block type: " + variantType);
        }
    }
}
