package fr.noahboos.essorrevamped.components.definitions.identifier;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class IdentifierService {
    public static Optional<Identifier> getIdentifier(ItemStack itemStack) {
        Identifier identifier = itemStack.get(EssorRevampedComponents.IDENTIFIER);

        if (identifier == null) {
            EssorRevamped.LOGGER.warn("No Identifier data component were found on {}.", itemStack.hashCode());
            return Optional.empty();
        }

        return Optional.of(identifier);
    }
}
