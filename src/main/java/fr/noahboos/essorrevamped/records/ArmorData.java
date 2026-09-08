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

    public ArmorData withArmor(double armor) {
        return new ArmorData(
            this.equipmentSlot(),
            this.itemStack(),
            armor,
            this.armorToughness(),
            this.enchantmentProtectionFactor()
        );
    }

    public ArmorData withArmorToughness(double armorToughness) {
        return new ArmorData(
            this.equipmentSlot(),
            this.itemStack(),
            this.armor(),
            armorToughness,
            this.enchantmentProtectionFactor()
        );
    }

    public ArmorData withEnchantmentProtectionFactor(int enchantmentProtectionFactor) {
        return new ArmorData(
            this.equipmentSlot(),
            this.itemStack(),
            this.armor(),
            this.armorToughness(),
            enchantmentProtectionFactor
        );
    }
}
