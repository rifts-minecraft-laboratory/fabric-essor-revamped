package fr.noahboos.essorrevamped.experiencetables;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityTypes;
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
        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-axe"), new ExperienceTable(Map.ofEntries(
            //
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-hoe"), new ExperienceTable(Map.ofEntries(
            //
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-pickaxe"), new ExperienceTable(Map.ofEntries(
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STONE), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.GRANITE), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DIORITE), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.ANDESITE), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SANDSTONE), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.RED_SANDSTONE), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.TUFF), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CINNABAR), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SULFUR), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SMOOTH_BASALT), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CALCITE), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.AMETHYST_BLOCK), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BUDDING_AMETHYST), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DRIPSTONE_BLOCK), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.COPPER_ORE), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.COAL_ORE), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.IRON_ORE), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.GOLD_ORE), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DIAMOND_ORE), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.EMERALD_ORE), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_COPPER_ORE), 1.875f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_COAL_ORE), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_IRON_ORE), 2.625f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_GOLD_ORE), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_DIAMOND_ORE), 3.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DEEPSLATE_EMERALD_ORE), 4.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.OBSIDIAN), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CRYING_OBSIDIAN), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.NETHERRACK), 0.125f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CRIMSON_NYLIUM), 0.125f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.WARPED_NYLIUM), 0.125f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BASALT), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BLACKSTONE), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_GOLD_ORE), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_QUARTZ_ORE), 2.625f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.ANCIENT_DEBRIS), 8.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.END_STONE), 1.5f)
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-shears"), new ExperienceTable(Map.ofEntries(
            //
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-shovel"), new ExperienceTable(Map.ofEntries(
            //
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "entity-killing-global"), new ExperienceTable(Map.ofEntries(
            Map.entry(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.ENDER_DRAGON), 500.0f),
            Map.entry(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.WITHER), 250.0f)
        )));
    }

    @Override
    public String getName() {
        return "Experience tables";
    }
}
