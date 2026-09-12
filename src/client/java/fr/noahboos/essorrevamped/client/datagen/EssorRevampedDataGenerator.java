package fr.noahboos.essorrevamped.client.datagen;

import fr.noahboos.essorrevamped.experiencetables.EssorRevampedExperienceTableProvider;
import fr.noahboos.essorrevamped.recipes.EssorRevampedRecipeProvider;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EssorRevampedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(EssorRevampedItemTagProvider::new);
        pack.addProvider(EssorRevampedExperienceTableProvider::new);
        pack.addProvider(EssorRevampedModelProvider::new);
        pack.addProvider(EssorRevampedRecipeProvider::new);
    }
}
