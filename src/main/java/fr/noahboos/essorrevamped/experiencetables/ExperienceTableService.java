package fr.noahboos.essorrevamped.experiencetables;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.enums.ActionType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StrictJsonParser;
import net.minecraft.world.item.ItemStack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExperienceTableService {
    public static ExperienceTable findExperienceTable(ResourceManager resourceManager, ItemStack itemStack, ActionType actionType) {
        Resource resource = null;
        try {
            if (itemStack.is(ItemTags.PICKAXES) && actionType.is(ActionType.MINING)) {
                resource = resourceManager.getResourceOrThrow(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "experience_tables/mining.json"));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (resource == null) return null;

        try (BufferedReader reader = resource.openAsReader()) {
            JsonElement jsonElement = StrictJsonParser.parse(reader);
            return ExperienceTable.CODEC.parse(JsonOps.INSTANCE, jsonElement).getOrThrow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
