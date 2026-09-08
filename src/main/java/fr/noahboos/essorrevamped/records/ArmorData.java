package fr.noahboos.essorrevamped.records;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record ArmorData(
    EquipmentSlot equipmentSlot,
    ItemStack itemStack,
    double armor,
    double armorToughness,
    int enchantmentProtectionFactor
) {
    public ArmorData(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        this(equipmentSlot, itemStack, 0, 0, 0);
    }
}
