package fr.noahboos.essorrevamped.events;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.attributes.AttributeHelper;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enums.ActionType;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTable;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTableService;
import fr.noahboos.essorrevamped.records.ArmorPieceData;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.ArrayList;
import java.util.List;

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

            ItemStack itemStack = player.getActiveItem();
            Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);

            if (progression == null) return;

            progression = ProgressionService.gainExperiencePoints(progression, experienceToGain);
            itemStack.set(EssorRevampedComponents.PROGRESSION, progression);
        });
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            List<ArmorPieceData> armorPieces = new ArrayList<>();
            armorPieces.add(new ArmorPieceData(EquipmentSlot.HEAD, entity.getItemBySlot(EquipmentSlot.HEAD)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.CHEST, entity.getItemBySlot(EquipmentSlot.CHEST)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.LEGS, entity.getItemBySlot(EquipmentSlot.LEGS)));
            armorPieces.add(new ArmorPieceData(EquipmentSlot.FEET, entity.getItemBySlot(EquipmentSlot.FEET)));

            armorPieces.forEach(armorPieceData -> {
                EssorRevamped.LOGGER.warn("Gathering data from " + armorPieceData.itemStack().getItemName().getString() + ".");
                ItemAttributeModifiers itemAttributeModifiers = armorPieceData.itemStack().get(DataComponents.ATTRIBUTE_MODIFIERS);
                if (itemAttributeModifiers == null) {
                    EssorRevamped.LOGGER.warn("Can't gather data from " + armorPieceData.itemStack().getItemName().getString() + ".");
                    return;
                }

                ArmorPieceData _armorPieceData = armorPieceData
                    .withArmor(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR))
                    .withArmorToughness(AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR_TOUGHNESS));

                armorPieces.set(armorPieces.indexOf(armorPieceData), _armorPieceData);
                EssorRevamped.LOGGER.warn("Gathered data from " + armorPieceData.itemStack().getItemName().getString() + ".");
            });

            armorPieces.forEach(armorPieceData -> {
                EssorRevamped.LOGGER.warn("Rewarding " + armorPieceData.itemStack().getItemName().getString() + " with experience.");
                Progression progression = armorPieceData.itemStack().get(EssorRevampedComponents.PROGRESSION);
                if (progression == null) {
                    EssorRevamped.LOGGER.warn("Can't reward " + armorPieceData.itemStack().getItemName().getString() + " as it has no progression data component.");
                    return;
                }

                float damageReductionPercentage = 0f;
                float damageMitigated = 0f;

                damageReductionPercentage = (float) Math.min(80, Math.max((4 / 5) * armorPieceData.armor(), 4 * armorPieceData.armor() - ((16 * baseDamageTaken) / (armorPieceData.armorToughness() + 8))));
                damageMitigated = baseDamageTaken * damageReductionPercentage / 100;

                float experienceToGain = damageMitigated * 7.5f;

                progression = ProgressionService.gainExperiencePoints(progression, experienceToGain);
                EssorRevamped.LOGGER.info(progression.toString()); // Log à effacer pour la mise en prod'.
                armorPieceData.itemStack().set(EssorRevampedComponents.PROGRESSION, progression);
                EssorRevamped.LOGGER.warn("Rewarded " + armorPieceData.itemStack().getItemName().getString() + " with experience.");
            });
        });
        EssorRevamped.LOGGER.info("Registered {}'s events.", EssorRevamped.MOD_ID);
    }
}
