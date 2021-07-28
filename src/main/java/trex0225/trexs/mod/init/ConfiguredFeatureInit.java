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

    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreWoolOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
      new Identifier("tutorial", "ore_wool_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWoolOverworld.getValue(), ORE_INDUSIUM_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWoolOverworld);
    }
}
