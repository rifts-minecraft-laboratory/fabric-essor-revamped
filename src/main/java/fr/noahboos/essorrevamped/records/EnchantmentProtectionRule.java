package fr.noahboos.essorrevamped.records;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Predicate;

public record EnchantmentProtectionRule(
    ResourceKey<Enchantment> enchantment,
    int baseEnchantmentProtectionFactor,
    Predicate<DamageSource> appliesTo
) {
    //
}
