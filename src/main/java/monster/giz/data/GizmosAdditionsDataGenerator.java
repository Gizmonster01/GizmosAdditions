package monster.giz.data;

import monster.giz.data.providers.GABlockLootTableProvider;
import monster.giz.data.providers.GABlockTagProvider;
import monster.giz.data.providers.GAModelProvider;
import monster.giz.data.providers.GARecipeProvider;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GizmosAdditionsDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		GALogger.log("Attempting to initialize data files");
		final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(GAModelProvider::new);
		pack.addProvider(GABlockLootTableProvider::new);
		pack.addProvider(GABlockTagProvider::new);
		pack.addProvider(GARecipeProvider::new);
	}
}
