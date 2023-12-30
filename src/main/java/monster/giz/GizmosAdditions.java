package monster.giz;

import monster.giz.blocks.GizmosAdditionsBlocks;
import monster.giz.util.GALogger;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GizmosAdditions implements ModInitializer {

	public static final GameRules.Key<GameRules.BooleanRule> ENDERMAN_GRIEFING = GameRuleRegistry.register("endermanGriefing", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> END_ACCESS = GameRuleRegistry.register("allowEndAccess", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.BooleanRule> LEATHER_TRAMPLE = GameRuleRegistry.register("doBootsTrample", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.IntRule> XP_DROP_PERCENTAGE = GameRuleRegistry.register("xpDropPercentage", GameRules.Category.DROPS, GameRuleFactory.createIntRule(-1, -1, 100));
	public static final String NAMESPACE = "gizmosadditions";

	@Override
	public void onInitialize() {
		GALogger.log("Initialized.");
		GizmosAdditionsBlocks.initialize();
	}
}