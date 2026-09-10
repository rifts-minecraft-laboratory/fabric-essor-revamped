package fr.noahboos.essorrevamped.events;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.attributes.AttributeHelper;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enchantments.EnchantmentHelper;
import fr.noahboos.essorrevamped.enums.ActionType;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTable;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTableService;
import fr.noahboos.essorrevamped.records.ArmorPieceData;
import fr.noahboos.essorrevamped.records.EnchantmentProtectionRule;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EssorRevampedEvents {
    public static void initialize() {
        EssorRevamped.LOGGER.info("Registering {}'s events.", EssorRevamped.MOD_ID);
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Progression::isApplicableTo , (builder, item) -> {
                builder.set(EssorRevampedComponents.PROGRESSION, new Progression());
            });
        });
        PlayerBlockBreakEvents.AFTER.register((world, player, blockPos, blockState, blockEntity) -> {
            ExperienceTable experienceTable = ExperienceTableService.findExperienceTable(world.getServer().getResourceManager(), player.getActiveItem(), ActionType.BLOCK_BREAKING);
            if (experienceTable == null) return;
            float experienceToGain = experienceTable.values().getOrDefault(BuiltInRegistries.BLOCK.getKey(blockState.getBlock()), 0f);

            ProgressionService.progressItem(player.getActiveItem(), experienceToGain);
        });
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
        EssorRevamped.LOGGER.info("Registered {}'s events.", EssorRevamped.MOD_ID);
    }
}
