package fr.noahboos.essorrevamped.components.definitions.progression;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.durability.DurabilityService;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ProgressionService {
    public static Optional<Progression> getProgression(ItemStack itemStack) {
        Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);

        if (progression == null) {
            EssorRevamped.LOGGER.warn("No Progression data component were found on {}.", itemStack.hashCode());
            return Optional.empty();
        }

        return Optional.of(progression);
    }

    public static void updateProgression(ItemStack itemStack, float experiencePoints) {
        getProgression(itemStack).ifPresent(progression -> {
            Progression _progression = progression
                .addExperiencePoints(experiencePoints)
                .addExperienceLevels();

            itemStack.set(EssorRevampedComponents.PROGRESSION, _progression);
        });

        DurabilityService.updateDurability(itemStack);
    }
}
