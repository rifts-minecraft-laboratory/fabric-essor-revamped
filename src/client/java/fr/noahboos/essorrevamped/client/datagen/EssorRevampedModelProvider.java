package fr.noahboos.essorrevamped.client.datagen;

import fr.noahboos.essorrevamped.items.EssorRevampedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class EssorRevampedModelProvider extends FabricModelProvider {
    public EssorRevampedModelProvider(FabricPackOutput fabricPackOutput) {
        super(fabricPackOutput);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        //
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        //
    }

    @Override
    public String getName() {
        return "EssorRevampedModelProvider";
    }
}