package fr.noahboos.essorrevamped.attributes;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class AttributeHelper {
    public static double getAttributeValue(ItemAttributeModifiers itemAttributeModifiers, Holder<Attribute> attribute) {
        for (var entry : itemAttributeModifiers.modifiers()) {
            if (entry.attribute() == attribute) return entry.modifier().amount();
        }

        return 0;
    }
}
