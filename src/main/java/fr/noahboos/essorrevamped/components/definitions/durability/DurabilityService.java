package fr.noahboos.essorrevamped.components.definitions.durability;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class DurabilityService {
    public static Optional<Durability> getDurability(ItemStack itemStack) {
        Durability durability = itemStack.get(EssorRevampedComponents.DURABILITY);

        if (durability == null) {
            EssorRevamped.LOGGER.warn("No Durability data component were found on {}.", itemStack.hashCode());
            return Optional.empty();
        }

        return Optional.of(durability);
    }

    public static void updateDurability(ItemStack itemStack) {
        getDurability(itemStack).ifPresent(durability -> {
            ProgressionService.getProgression(itemStack).ifPresent(progression -> {
                Durability _durability = durability
                    .addDurabilityPoints(progression.experienceLevel());
                int effectiveMaximumDurabilityPoints = itemStack.getItem().getDefaultInstance().getMaxDamage() + _durability.durabilityPoints();

                itemStack.set(EssorRevampedComponents.DURABILITY, _durability);
                itemStack.set(DataComponents.MAX_DAMAGE, effectiveMaximumDurabilityPoints);
            });
        });
    }
}
