package fr.noahboos.essorrevamped.components.definitions.durability;

import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class DurabilityService {
    public static void updateDurability(ItemStack itemStack) {
        Durability durability = itemStack.get(EssorRevampedComponents.DURABILITY);
        Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);
        if (durability == null || progression == null) return;

        durability = durability.withDurability(Durability.step * progression.experienceLevel());

        itemStack.set(EssorRevampedComponents.DURABILITY, durability);
        itemStack.set(DataComponents.MAX_DAMAGE, itemStack.getItem().getDefaultInstance().getMaxDamage() + durability.durability());

    }
}
