package fr.noahboos.essorrevamped.experiencetables;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.enums.ActionType;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StrictJsonParser;
import net.minecraft.world.item.ItemStack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class ExperienceTableService {
    public static Optional<ExperienceTable> findExperienceTable(ResourceManager resourceManager, ItemStack itemStack, ActionType actionType) {
        if (itemStack.isEmpty()) return Optional.empty();

        String path = "";

        if (itemStack.is(ItemTags.AXES) && actionType.is(ActionType.BLOCK_BREAKING)) {
            path = "experience_tables/block-breaking-axe.json";
        } else if (itemStack.is(ItemTags.HOES) && actionType.is(ActionType.BLOCK_BREAKING)) {
            path = "experience_tables/block-breaking-hoe.json";
        } else if (itemStack.is(ItemTags.PICKAXES) && actionType.is(ActionType.BLOCK_BREAKING)) {
            path = "experience_tables/block-breaking-pickaxe.json";
        } else if (itemStack.is(ItemTags.SHOVELS) && actionType.is(ActionType.BLOCK_BREAKING)) {
            path = "experience_tables/block-breaking-shovel.json";
        } else if (itemStack.is(ConventionalItemTags.MELEE_WEAPON_TOOLS) || itemStack.is(ConventionalItemTags.RANGED_WEAPON_TOOLS)) {
            path = "experience_tables/killing-global.json";
        }

        if (path.isEmpty()) return Optional.empty();

        Optional<Resource> resource = resourceManager.getResource(Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, path));
        if (resource.isEmpty()) {
            EssorRevamped.LOGGER.warn("Couldn't parse an experience table. The provided path is not recognized.");
            return Optional.empty();
        }

        try (BufferedReader reader = resource.get().openAsReader()) {
            JsonElement jsonElement = StrictJsonParser.parse(reader);
            ExperienceTable experienceTable = ExperienceTable.CODEC.parse(JsonOps.INSTANCE, jsonElement).getOrThrow();
            if (experienceTable == null) return Optional.empty();
            return Optional.of(experienceTable);
        } catch (IOException e) {
            EssorRevamped.LOGGER.error("Couldn't parse an experience table : {}", String.valueOf(e));
            return Optional.empty();
        }
    }
}
