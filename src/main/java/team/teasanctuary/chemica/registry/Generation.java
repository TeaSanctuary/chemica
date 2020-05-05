package team.teasanctuary.chemica.registry;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.function.Consumer;

public class Generation {
    // TODO: Make something like generation class
    private static void handleBiome(Biome biome) {
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
                                    8, //Number of veins per chunk
                                    0, //Bottom Offset
                                    0, //Min y level
                                    64 //Max y level
                            ))));
        }
    }

    public static void init() {
        //Loop over existing biomes
        Registry.BIOME.forEach(Generation::handleBiome);

        //Listen for other biomes being registered
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }
}
