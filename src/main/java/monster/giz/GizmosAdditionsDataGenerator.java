package monster.giz;

import monster.giz.data.GAModelGenerator;
import monster.giz.util.GALogger;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.util.Identifier;

public class GizmosAdditionsDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		GALogger.log("Attempting to initialize data files");
		final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(GAModelGenerator::new);
	}
}
