package fr.noahboos.essorrevamped.events;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.attributes.AttributeHelper;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enums.ActionType;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTable;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTableService;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

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
            ItemStack[] armorPieces = {
                entity.getItemBySlot(EquipmentSlot.HEAD),
                entity.getItemBySlot(EquipmentSlot.CHEST),
                entity.getItemBySlot(EquipmentSlot.LEGS),
                entity.getItemBySlot(EquipmentSlot.FEET)
            };

            for (ItemStack itemStack : armorPieces) {
                Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);
                if (progression == null) continue;

                ItemAttributeModifiers itemAttributeModifiers = itemStack.get(DataComponents.ATTRIBUTE_MODIFIERS);
                if (itemAttributeModifiers == null) continue;

                double armor = AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR);
                double armorToughness = AttributeHelper.getAttributeValue(itemAttributeModifiers, Attributes.ARMOR_TOUGHNESS);

                // Minecraft's formula used to compute the damage reduction as a percentage.
                float damageReductionPercentage = (float) Math.min(80, Math.max((4 / 5) * armor, 4 * armor - ((16 * baseDamageTaken) / (armorToughness + 8))));
                float damageMitigated = baseDamageTaken * damageReductionPercentage / 100;
                float experienceToGain = damageMitigated * 7.5f;

                progression = ProgressionService.gainExperiencePoints(progression, experienceToGain);
                EssorRevamped.LOGGER.info(progression.toString());
                itemStack.set(EssorRevampedComponents.PROGRESSION, progression);
            }
        });
        EssorRevamped.LOGGER.info("Registered {}'s events.", EssorRevamped.MOD_ID);
    }
}
