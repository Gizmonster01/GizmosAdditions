package monster.giz.data.providers;

import monster.giz.blocks.GizmosAdditionsBlocks;
import monster.giz.blocks.types.GABlockType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GABlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public GABlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        appendTag(BlockTags.PICKAXE_MINEABLE, GizmosAdditionsBlocks.MOSSY_COBBLED_DEEPSLATE_SET.getBlocks());
        appendTag(BlockTags.PICKAXE_MINEABLE, GizmosAdditionsBlocks.MOSSY_DEEPSLATE_BRICKS_SET.getBlocks());
        appendTag(BlockTags.PICKAXE_MINEABLE, GizmosAdditionsBlocks.POLISHED_AMETHYST_SET.getBlocks());
    }

    private void appendTag(TagKey tag, Map<GABlockType, Block> map) {
        FabricTagBuilder builder = getOrCreateTagBuilder(tag);
        for (GABlockType type : map.keySet()) {
            builder.add(map.get(type));
            if (type == GABlockType.WALL) {
                getOrCreateTagBuilder(BlockTags.WALLS).add(map.get(type));
            }
        }
    }
}
