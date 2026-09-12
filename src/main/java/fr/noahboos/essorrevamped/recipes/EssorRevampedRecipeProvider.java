package fr.noahboos.essorrevamped.recipes;

import fr.noahboos.essorrevamped.items.EssorRevampedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class EssorRevampedRecipeProvider extends FabricRecipeProvider {
    public EssorRevampedRecipeProvider(FabricPackOutput fabricPackOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(fabricPackOutput, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shaped(RecipeCategory.MISC, EssorRevampedItems.MASTERY_LEVEL_UPGRADE_SMITHING_TEMPLATE, 1)
                    .pattern("aaa")
                    .pattern("aba")
                    .pattern("aca")
                    .define('a', Items.AMETHYST_SHARD)
                    .define('b', Items.NETHER_STAR)
                    .define('c', Items.PURPUR_SLAB)
                    .group("smithing_templates")
                    .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD))
                    .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                    .unlockedBy("has_purpur_slab", has(Items.PURPUR_SLAB))
                    .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
