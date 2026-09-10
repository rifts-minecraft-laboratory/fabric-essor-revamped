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
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AfterDamageEventHandler {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            List<ArmorPieceData> armorPieces = new ArrayList<>();
            armorPieces.add(new ArmorPieceData(EquipmentSlot.HEAD, entity.getItemBySlot(EquipmentSlot.HEAD)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.CHEST, entity.getItemBySlot(EquipmentSlot.CHEST)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.LEGS, entity.getItemBySlot(EquipmentSlot.LEGS)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.FEET, entity.getItemBySlot(EquipmentSlot.FEET)));

            if (source.is(DamageTypes.DROWN)) return;

            armorPieces.forEach(armorPieceData -> {
                EssorRevamped.LOGGER.info("Gathering data from {}.", armorPieceData.itemStack().getItemName().getString());

                ItemAttributeModifiers itemAttributeModifiers = armorPieceData.itemStack().get(DataComponents.ATTRIBUTE_MODIFIERS);
                if (itemAttributeModifiers == null) {
                    EssorRevamped.LOGGER.warn("Can't gather data from {} as it has no attribute modifier data component.", armorPieceData.itemStack().getItemName().getString());
                    return;
                }

                ArmorPieceData _armorPieceData = armorPieceData
                    .withArmor(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR))
                    .withArmorToughness(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR_TOUGHNESS));

                for (EnchantmentProtectionRule enchantmentProtectionRule : EnchantmentHelper.enchantmentProtectionRules) {
                    if (!enchantmentProtectionRule.appliesTo().test(source)) continue;

                    Map.Entry<Holder<Enchantment>, Integer> enchantment = EnchantmentHelper.getEnchantment(_armorPieceData.itemStack().getEnchantments(), enchantmentProtectionRule.enchantment());

                    if (enchantment == null) continue;

                    _armorPieceData = _armorPieceData.withEnchantmentProtectionFactor(_armorPieceData.enchantmentProtectionFactor() + (enchantmentProtectionRule.baseEnchantmentProtectionFactor() * enchantment.getValue()));
                };

                EssorRevamped.LOGGER.info("Gathered data from {}.", _armorPieceData.itemStack().getItemName().getString());
                EssorRevamped.LOGGER.info("Rewarding {} with experience.", _armorPieceData.itemStack().getItemName().getString());

                if (source.is(DamageTypes.FALL)) {
                    if (_armorPieceData.equipmentSlot() != EquipmentSlot.FEET) return;
                }

                float experienceToGain = (float) (damageTaken * (1f + (_armorPieceData.armor() * 0.05f) + (_armorPieceData.armorToughness() * 0.10f) + (_armorPieceData.enchantmentProtectionFactor() * 0.10f))) * 2.0f;

                ProgressionService.progressItem(_armorPieceData.itemStack(), experienceToGain);

                EssorRevamped.LOGGER.info("Rewarded {} with experience.", _armorPieceData.itemStack().getItemName().getString());
            });
        });
    }
}
