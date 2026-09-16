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
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.ACACIA_LOG), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_ACACIA_LOG), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.ACACIA_WOOD), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_ACACIA_WOOD), 1.75f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BAMBOO), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BAMBOO_BLOCK), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_BAMBOO_BLOCK), 2.25f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BIRCH_LOG), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_BIRCH_LOG), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BIRCH_WOOD), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_BIRCH_WOOD), 1.25f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CHERRY_LOG), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_CHERRY_LOG), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CHERRY_WOOD), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_CHERRY_WOOD), 2.5f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DARK_OAK_LOG), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_DARK_OAK_LOG), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DARK_OAK_WOOD), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_DARK_OAK_WOOD), 2.0f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.JUNGLE_LOG), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_JUNGLE_LOG), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.JUNGLE_WOOD), 2.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_JUNGLE_WOOD), 2.25f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MANGROVE_LOG), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_MANGROVE_LOG), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MANGROVE_WOOD), 3.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_MANGROVE_WOOD), 3.0f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MUSHROOM_STEM), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.RED_MUSHROOM_BLOCK), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BROWN_MUSHROOM_BLOCK), 2.0f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.OAK_LOG), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_OAK_LOG), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.OAK_WOOD), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_OAK_WOOD), 1.0f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.PALE_OAK_LOG), 2.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_PALE_OAK_LOG), 2.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.PALE_OAK_WOOD), 2.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_PALE_OAK_WOOD), 2.75f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SPRUCE_LOG), 1.50f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_SPRUCE_LOG), 1.50f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SPRUCE_WOOD), 1.50f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_SPRUCE_WOOD), 1.50f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CRIMSON_STEM), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_CRIMSON_STEM), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CRIMSON_HYPHAE), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_CRIMSON_HYPHAE), 2.5f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.WARPED_STEM), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_WARPED_STEM), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.WARPED_HYPHAE), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.STRIPPED_WARPED_HYPHAE), 2.5f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MELON), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.PUMPKIN), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CARVED_PUMPKIN), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.JACK_O_LANTERN), 1.25f)
        )));

        provider.accept(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "block-breaking-hoe"), new ExperienceTable(Map.ofEntries(
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_WART_BLOCK), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.WARPED_WART_BLOCK), 2.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SHROOMLIGHT), 2.5f),

            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.BEETROOTS), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CARROTS), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.POTATOES), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.WHEAT), 1.0f)
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
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.GRASS_BLOCK), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DIRT_PATH), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.DIRT), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.COARSE_DIRT), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.ROOTED_DIRT), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.FARMLAND), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.PODZOL), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MYCELIUM), 1.75f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SNOW), 1.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MUD), 2.0f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.CLAY), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.GRAVEL), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SAND), 1.25f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.MOSS_BLOCK), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.PALE_MOSS_BLOCK), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SOUL_SAND), 1.5f),
            Map.entry(BuiltInRegistries.BLOCK.getKey(Blocks.SOUL_SOIL), 1.5f)
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
