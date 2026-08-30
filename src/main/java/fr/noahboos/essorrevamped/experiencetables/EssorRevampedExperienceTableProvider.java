package fr.noahboos.essorrevamped.experiencetables;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EssorRevampedExperienceTableProvider extends FabricCodecDataProvider<ExperienceTable> {
    public EssorRevampedExperienceTableProvider(FabricPackOutput fabricPackOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(fabricPackOutput, registriesFuture, PackOutput.Target.DATA_PACK, "experience_tables", ExperienceTable.CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, ExperienceTable> provider, HolderLookup.Provider registryLookup) {
        // Experience tables are defined here.
    }

    @Override
    public String getName() {
        return "Experience tables";
    }
}
