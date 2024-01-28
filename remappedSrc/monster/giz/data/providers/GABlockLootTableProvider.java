package monster.giz.data.providers;

import monster.giz.blocks.GizmosAdditionsBlocks;
import monster.giz.blocks.types.GABlockType;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

import java.util.Map;

public class GABlockLootTableProvider extends FabricBlockLootTableProvider {
    public GABlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        createBlockLootTables(GizmosAdditionsBlocks.MOSSY_COBBLED_DEEPSLATE_SET.getBlocks());
        createBlockLootTables(GizmosAdditionsBlocks.MOSSY_DEEPSLATE_BRICKS_SET.getBlocks());
        createBlockLootTables(GizmosAdditionsBlocks.POLISHED_AMETHYST_SET.getBlocks());
    }

    public void createBlockLootTables(Map<GABlockType, Block> map) {
        for (GABlockType type : map.keySet()) {
            Block block = map.get(type);
            if (type == GABlockType.SLAB) {
                this.addDrop(block, this.slabDrops(block));
            }
            GALogger.log("Adding drops for " + block.getTranslationKey() + " (" + type.getName() + ")");
            this.addDrop(block);
        }
    }
}