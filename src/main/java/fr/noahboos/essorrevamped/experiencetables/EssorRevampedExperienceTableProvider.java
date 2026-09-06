package fr.noahboos.essorrevamped.experiencetables;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EssorRevampedExperienceTableProvider extends FabricCodecDataProvider<ExperienceTable> {
    public EssorRevampedExperienceTableProvider(FabricPackOutput fabricPackOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(fabricPackOutput, registriesFuture, PackOutput.Target.DATA_PACK, "experience_tables", ExperienceTable.CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, ExperienceTable> provider, HolderLookup.Provider registryLookup) {
        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-axe"), new ExperienceTable(Map.of(
            BuiltInRegistries.BLOCK.getKey(Blocks.OAK_LOG), 7.5f
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-hoe"), new ExperienceTable(Map.of(
            BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_WART_BLOCK), 12.5f
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-pickaxe"), new ExperienceTable(Map.of(
            BuiltInRegistries.BLOCK.getKey(Blocks.STONE), 10f,
            BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE), 20f
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-shovel"), new ExperienceTable(Map.of(
            BuiltInRegistries.BLOCK.getKey(Blocks.GRASS_BLOCK), 25f
        )));
    }

    @Override
    public String getName() {
        return "Experience tables";
    }
}
