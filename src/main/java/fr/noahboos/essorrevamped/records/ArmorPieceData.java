package fr.noahboos.essorrevamped.records;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record ArmorPieceData(
    EquipmentSlot equipmentSlot,
    ItemStack itemStack,
    double armor,
    double armorToughness,
    int enchantmentProtectionFactor
) {
    public ArmorPieceData(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        this(equipmentSlot, itemStack, 0, 0, 0);
    }

    public ArmorPieceData withArmor(double armor) {
        return new ArmorPieceData(
            this.equipmentSlot(),
            this.itemStack(),
            armor,
            this.armorToughness(),
            this.enchantmentProtectionFactor()
        );
    }

    public ArmorPieceData withArmorToughness(double armorToughness) {
        return new ArmorPieceData(
            this.equipmentSlot(),
            this.itemStack(),
            this.armor(),
            armorToughness,
            this.enchantmentProtectionFactor()
        );
    }

    public ArmorPieceData withEnchantmentProtectionFactor(int enchantmentProtectionFactor) {
        return new ArmorPieceData(
            this.equipmentSlot(),
            this.itemStack(),
            this.armor(),
            this.armorToughness(),
            enchantmentProtectionFactor
        );
    }
}
