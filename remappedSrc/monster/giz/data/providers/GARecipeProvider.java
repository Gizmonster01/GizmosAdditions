package monster.giz.data.providers;

import monster.giz.blocks.GizmosAdditionsBlocks;
import monster.giz.blocks.types.GABlockType;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.Map;

public class GARecipeProvider extends FabricRecipeProvider {

    public GARecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        createVariantRecipesFromBlockSet(
                exporter,
                GizmosAdditionsBlocks.MOSSY_COBBLED_DEEPSLATE_SET.getBlocks(),
                true);
        createVariantRecipesFromBlockSet(
                exporter,
                GizmosAdditionsBlocks.MOSSY_DEEPSLATE_BRICKS_SET.getBlocks(),
                true);
        createVariantRecipesFromBlockSet(
                exporter,
                GizmosAdditionsBlocks.POLISHED_AMETHYST_SET.getBlocks(),
                true, Blocks.AMETHYST_BLOCK);
    }

    public void createVariantRecipesFromBlockSet(RecipeExporter exporter, Map<GABlockType, Block> map, boolean supportStonecutter) {
        createVariantRecipesFromBlockSet(exporter, map, supportStonecutter, null);
    }

    public void createVariantRecipesFromBlockSet(RecipeExporter exporter, Map<GABlockType, Block> map, boolean supportStonecutter, Block underlyingIngredient) {
        for (GABlockType type : map.keySet()) {
            Block baseBlock = map.get(GABlockType.BASE);
            Block block = map.get(type);
            if (type == GABlockType.BASE) {
                continue;
            }
            switch(type) {
                case SLAB -> offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block, baseBlock);
                case STAIRS -> offerStairsRecipe(exporter, block, baseBlock);
                case WALL -> offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block, baseBlock);
            }
            if (supportStonecutter) {
                int count = 1;
                if (type == GABlockType.SLAB) {
                    count = 2;
                }
                offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block, baseBlock, count);
                if (underlyingIngredient != null) {
                    offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, block, underlyingIngredient, count);
                }
            }
        }
        if (supportStonecutter) {
            if (underlyingIngredient != null) {
                offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, map.get(GABlockType.BASE), underlyingIngredient, 1);
            }
        }
    }

    public static void offerStairsRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

}
