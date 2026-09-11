package fr.noahboos.essorrevamped.events.handlers.entity;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.attributes.AttributeHelper;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enchantments.EnchantmentHelper;
import fr.noahboos.essorrevamped.records.ArmorPieceData;
import fr.noahboos.essorrevamped.records.EnchantmentProtectionRule;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Map;
import java.util.Optional;

public class AfterDamageEventHandler {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, damageSource, baseDamageTaken, damageTaken, blocked) -> {
            AfterDamageEventHandler.handleArmor(entity, damageSource, damageTaken);
            AfterDamageEventHandler.handleShield(entity, baseDamageTaken, blocked);
        });
    }

    // <editor-fold desc="Region - Hurt entity's armor handling after damage has been taken." defaultstate="collapsed">
    public static void handleArmor(LivingEntity entity, DamageSource damageSource, float damageTaken) {
        if (damageSource.is(DamageTypes.DROWN)) return;

        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (equipmentSlot.getType() != EquipmentSlot.Type.HUMANOID_ARMOR) continue;

            Optional<ArmorPieceData> armorPieceData = AfterDamageEventHandler.gatherArmorData(equipmentSlot, entity, damageSource);
            if (armorPieceData.isEmpty()) continue;
            AfterDamageEventHandler.rewardArmorPiece(armorPieceData.get(), damageSource, damageTaken);
        }
    }

    public static Optional<ArmorPieceData> gatherArmorData(EquipmentSlot equipmentSlot, LivingEntity entity, DamageSource damageSource) {
        ArmorPieceData armorPieceData = new ArmorPieceData(equipmentSlot, entity.getItemBySlot(equipmentSlot));
        if (armorPieceData.itemStack().getItem() == Items.AIR) return Optional.empty();

        EssorRevamped.LOGGER.info("Gathering data from {}.", armorPieceData.itemStack().getItemName().getString());

        ItemAttributeModifiers itemAttributeModifiers = armorPieceData.itemStack().get(DataComponents.ATTRIBUTE_MODIFIERS);
        if (itemAttributeModifiers == null) {
            EssorRevamped.LOGGER.warn("Can't gather data from {} as it has no attribute modifier data component.", armorPieceData.itemStack().getItemName().getString());
            return Optional.empty();
        }

        ArmorPieceData _armorPieceData = armorPieceData
            .withArmor(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR))
            .withArmorToughness(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR_TOUGHNESS));

        for (EnchantmentProtectionRule enchantmentProtectionRule : EnchantmentHelper.enchantmentProtectionRules) {
            if (!enchantmentProtectionRule.appliesTo().test(damageSource)) continue;

            Map.Entry<Holder<Enchantment>, Integer> enchantment = EnchantmentHelper.getEnchantment(_armorPieceData.itemStack().getEnchantments(), enchantmentProtectionRule.enchantment());

            if (enchantment == null) continue;

            _armorPieceData = _armorPieceData.withEnchantmentProtectionFactor(_armorPieceData.enchantmentProtectionFactor() + (enchantmentProtectionRule.baseEnchantmentProtectionFactor() * enchantment.getValue()));
        }

        EssorRevamped.LOGGER.info("Gathered data from {}.", _armorPieceData.itemStack().getItemName().getString());

        return Optional.of(_armorPieceData);
    }

    public static void rewardArmorPiece(ArmorPieceData armorPieceData, DamageSource damageSource, float damageTaken) {
        EssorRevamped.LOGGER.info("Rewarding {} with experience.", armorPieceData.itemStack().getItemName().getString());

        if (damageSource.is(DamageTypes.FALL)) {
            if (armorPieceData.equipmentSlot() != EquipmentSlot.FEET) return;
        }

        float experienceToGain = (float) (damageTaken * (1f + (armorPieceData.armor() * 0.05f) + (armorPieceData.armorToughness() * 0.10f) + (armorPieceData.enchantmentProtectionFactor() * 0.10f))) * 2.0f;

        ProgressionService.progressItem(armorPieceData.itemStack(), experienceToGain);

        EssorRevamped.LOGGER.info("Rewarded {} with experience.", armorPieceData.itemStack().getItemName().getString());
    }
    // </editor-fold>

    // <editor-fold desc="Region - Hurt entity's shield handling after damage has been blocked." defaultstate="collapsed">
    public static void handleShield(LivingEntity entity, float baseDamageTaken, boolean blocked) {
        if (!blocked) return;

        ItemStack shield = ItemStack.EMPTY;
        if (entity.getOffhandItem().getItem() == Items.SHIELD) shield = entity.getOffhandItem();
        if (entity.getMainHandItem().getItem() == Items.SHIELD) shield = entity.getMainHandItem();
        if (shield.isEmpty()) return;

        EssorRevamped.LOGGER.info("Rewarding {} with experience.", shield.getItemName().getString());

        float experienceToGain = (float) (baseDamageTaken * 4.0f);

        ProgressionService.progressItem(shield, experienceToGain);

        EssorRevamped.LOGGER.info("Rewarded {} with experience.", shield.getItemName().getString());
    }
    // </editor-fold>
}
