package fr.noahboos.essorrevamped.components.definitions.progression;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.durability.DurabilityService;
import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperienceToastPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
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

    public static void updateProgression(ServerPlayer serverPlayer, ItemStack itemStack, float experiencePoints) {
        getProgression(itemStack).ifPresent(progression -> {
            float previousExperiencePoints = progression.experiencePoints();

            Progression _progression = progression
                .addExperiencePoints(experiencePoints)
                .addExperienceLevels();

            itemStack.set(EssorRevampedComponents.PROGRESSION, _progression);

            ServerPlayNetworking.send(serverPlayer, new AddExperienceToastPayload(
                itemStack,
                _progression.experiencePoints() - previousExperiencePoints
            ));
        });

        DurabilityService.updateDurability(itemStack);
    }
}
