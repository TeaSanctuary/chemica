package team.teasanctuary.chemica.registry;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.BiomeSource;

public class Generation {
    // TODO: It looks like we don't need Generation class anymore as we have Mixin.
    /* private static void handleBiome(Codec<? extends BiomeSource> codec) {
        if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.MALACHITE_ORE_BLOCK.getDefaultState(),
                                    3 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    5, //Number of veins per chunk
                                    0, //Bottom Offset
                                    50, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.ARGENTITE_ORE_BLOCK.getDefaultState(),
                                    15 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.CHALCOPYRITE_ORE_BLOCK.getDefaultState(),
                                    25 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.GALENA_ORE_BLOCK.getDefaultState(),
                                    45 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    // TODO: Check if cuprite spawns with malachite
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.CUPRITE_ORE_BLOCK.getDefaultState(),
                                    3 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    5, //Number of veins per chunk
                                    0, //Bottom Offset
                                    50, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.RUTILE_ORE_BLOCK.getDefaultState(),
                                    4 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    20 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.SILVER_ORE_BLOCK.getDefaultState(),
                                    3 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    5, //Number of veins per chunk
                                    0, //Bottom Offset
                                    10, //Min y level
                                    40 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.SPHALERITE_ORE_BLOCK.getDefaultState(),
                                    30 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    // TODO: Check if zincite spaws with sphalerite
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.ZINCITE_ORE_BLOCK.getDefaultState(),
                                    30 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    1, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    75 //Max y level
                            ))));
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                            new OreFeatureConfig(
                                    OreFeatureConfig.Target.NATURAL_STONE,
                                    Ores.CASSITERITE_ORE_BLOCK.getDefaultState(),
                                    5 //Ore vein size
                            )).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                    10, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    50 //Max y level
                            ))));
        }
    } */

    public static void init() {
        //Loop over existing biomes
        //Registry.BIOME_SOURCE.forEach(Generation::handleBiome);
        //DynamicRegistryCallback.callback(Registry.BIOME_KEY).register();


        //Listen for other biomes being registered
        //RegistryEntryAddedCallback.event(Registry.BIOME_SOURCE).register((i, identifier, biome) -> handleBiome(biome));
    }
}
