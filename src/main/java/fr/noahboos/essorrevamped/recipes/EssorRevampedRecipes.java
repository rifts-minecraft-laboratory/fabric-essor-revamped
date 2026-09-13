package fr.noahboos.essorrevamped.recipes;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.recipes.definitions.progression.masterylevel.MasteryLevelSmithingDemoSlotDisplay;
import fr.noahboos.essorrevamped.recipes.definitions.progression.masterylevel.MasteryLevelSmithingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EssorRevampedRecipes {
    public static void initialize() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "progression_mastery_level_smithing"), MasteryLevelSmithingRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.SLOT_DISPLAY, Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "progression_mastery_level_smithing_display"), MasteryLevelSmithingDemoSlotDisplay.TYPE);
    }
}
