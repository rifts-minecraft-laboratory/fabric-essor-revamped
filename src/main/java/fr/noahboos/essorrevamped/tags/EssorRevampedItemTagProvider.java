package fr.noahboos.essorrevamped.tags;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class EssorRevampedItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public EssorRevampedItemTagProvider(FabricPackOutput fabricPackOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(fabricPackOutput, registriesFuture);
    }

    public static final TagKey<Item> HAS_PROGRESSION = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "has_progression"));

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(HAS_PROGRESSION)
            .addOptionalTag(ConventionalItemTags.HUMANOID_ARMORS)
            .addOptionalTag(ConventionalItemTags.TOOLS)
            .addOptional(ItemIds.ELYTRA)
            .remove(ItemIds.BRUSH)
            .remove(ItemIds.FISHING_ROD)
            .remove(ItemIds.FLINT_AND_STEEL)
            .setReplace(true);
    }
}
