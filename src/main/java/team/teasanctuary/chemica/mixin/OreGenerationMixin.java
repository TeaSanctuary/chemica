package team.teasanctuary.chemica.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class OreGenerationMixin {

    @Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
        // builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, Ores.ORE_ZINCITE);
        // TODO: Overworld materials!
    }

    @Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addNetherMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
    private static void addNetherMinables(GenerationSettings.Builder builder, CallbackInfo ci) {
        // TODO: Nether materials!
    }

}
