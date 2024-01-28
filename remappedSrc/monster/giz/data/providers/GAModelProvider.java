package monster.giz.data.providers;

import monster.giz.blocks.GizmosAdditionsBlocks;
import monster.giz.blocks.types.GABlockType;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import java.util.Map;

public class GAModelProvider extends FabricModelProvider {
    public GAModelProvider(FabricDataOutput generator) {
        super(generator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        createModels(blockStateModelGenerator, GizmosAdditionsBlocks.MOSSY_COBBLED_DEEPSLATE_SET.getBlocks());
        createModels(blockStateModelGenerator, GizmosAdditionsBlocks.MOSSY_DEEPSLATE_BRICKS_SET.getBlocks());
        createModels(blockStateModelGenerator, GizmosAdditionsBlocks.POLISHED_AMETHYST_SET.getBlocks());
    }

    public void createModels(BlockStateModelGenerator blockStateModelGenerator, Map<GABlockType, Block> map) {
        Block base = map.get(GABlockType.BASE);
        GALogger.log("Generating models for base " + base.getTranslationKey());
        BlockStateModelGenerator.BlockTexturePool pool = blockStateModelGenerator.registerCubeAllModelTexturePool(base);
        for (GABlockType type : map.keySet()) {
            if (type == GABlockType.BASE) {
                continue;
            }
            GALogger.log("Generating models for " + type.getName() + " variant " + map.get(type).getTranslationKey());
            switch(type) {
                case SLAB -> pool.slab(map.get(GABlockType.SLAB));
                case STAIRS -> pool.stairs(map.get(GABlockType.STAIRS));
                case WALL -> pool.wall(map.get(GABlockType.WALL));
                default -> {
                    continue;
                }
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

}
