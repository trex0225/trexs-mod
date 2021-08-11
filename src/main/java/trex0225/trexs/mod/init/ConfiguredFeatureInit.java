package trex0225.trexs.mod.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import trex0225.trexs.mod.TrexsMod;

public class ConfiguredFeatureInit {
    private static ConfiguredFeature<?, ?> ORE_INDUSIUM_OVERWORLD = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      BlockInit.INDUSIUM_ORE.getDefaultState(),
      9))
    .range(new RangeDecoratorConfig(
      UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(64))))
    .spreadHorizontally()
    .repeat(20);

    private static ConfiguredFeature<?, ?> ORE_MITHRIL_OVERWORLD = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      BlockInit.MITHRIL_ORE.getDefaultState(),
      8))
    .range(new RangeDecoratorConfig(
      UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(16))))
    .spreadHorizontally()
    .repeat(1);

    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreIndusiumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
      new Identifier(TrexsMod.MOD_ID, "ore_indusium_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreIndusiumOverworld.getValue(), ORE_INDUSIUM_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreIndusiumOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> oreMithrilOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
      new Identifier(TrexsMod.MOD_ID, "ore_mithril_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreMithrilOverworld.getValue(), ORE_MITHRIL_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreMithrilOverworld);
    }
}
