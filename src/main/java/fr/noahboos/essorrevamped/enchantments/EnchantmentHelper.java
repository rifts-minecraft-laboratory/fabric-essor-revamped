package fr.noahboos.essorrevamped.enchantments;

import fr.noahboos.essorrevamped.records.EnchantmentProtectionRule;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.List;
import java.util.Map;


public class EnchantmentHelper {
    public static Map.Entry<Holder<Enchantment>, Integer> getEnchantment(ItemEnchantments itemEnchantments, ResourceKey<Enchantment> enchantment) {
        for (var entry : itemEnchantments.entrySet()) {
            if (entry.getKey().is(enchantment)) return entry;
        }

        return null;
    }

    public static int getEnchantmentLevel(ItemEnchantments itemEnchantments, ResourceKey<Enchantment> enchantment) {
        for (var entry : itemEnchantments.entrySet()) {
            if (entry.getKey().is(enchantment)) return entry.getIntValue();
        }

        return 0;
    }

    public static final List<EnchantmentProtectionRule> enchantmentProtectionRules = List.of(
        new EnchantmentProtectionRule(
            Enchantments.PROTECTION,
            1,
            damageSource -> !damageSource.is(DamageTypes.SONIC_BOOM)
                && !damageSource.is(DamageTypes.STARVE)
                && !damageSource.is(DamageTypes.FELL_OUT_OF_WORLD)
                && !damageSource.is(DamageTypes.GENERIC_KILL)
        ),
        new EnchantmentProtectionRule(
            Enchantments.FIRE_PROTECTION,
            2,
            damageSource -> damageSource.is(DamageTypes.FIREBALL)
                || damageSource.is(DamageTypes.FIREWORKS)
                || damageSource.is(DamageTypes.IN_FIRE)
                || damageSource.is(DamageTypes.ON_FIRE)
                || damageSource.is(DamageTypes.UNATTRIBUTED_FIREBALL)
                || damageSource.is(DamageTypes.CAMPFIRE)
        ),
        new EnchantmentProtectionRule(
            Enchantments.BLAST_PROTECTION,
            2,
            damageSource -> damageSource.is(DamageTypes.EXPLOSION)
                || damageSource.is(DamageTypes.PLAYER_EXPLOSION)
        ),
        new EnchantmentProtectionRule(
            Enchantments.PROJECTILE_PROTECTION,
            2,
            damageSource -> damageSource.is(DamageTypes.ARROW)
                || damageSource.is(DamageTypes.TRIDENT)
                || damageSource.is(DamageTypes.FIREBALL)
                || damageSource.is(DamageTypes.UNATTRIBUTED_FIREBALL)
        ),
        new EnchantmentProtectionRule(
            Enchantments.FEATHER_FALLING,
            3,
            damageSource -> damageSource.is(DamageTypes.FALL)
        )
    );
}
