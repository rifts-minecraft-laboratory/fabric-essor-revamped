package fr.noahboos.essorrevamped.events.handlers.entity;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enums.ActionType;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTable;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTableService;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class AfterDeathEventHandler {
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((victim, damageSource) -> {
            AfterDeathEventHandler.handleWeapon(victim, damageSource);
        });
    }

    // <editor-fold desc="Region - Damage source's weapon handling after an entity has died." defaultstate="collapsed">
    public static void handleWeapon(LivingEntity victim, DamageSource damageSource) {
        if (!(damageSource.getEntity() instanceof LivingEntity killer)) return;

        ItemStack weapon = ItemStack.EMPTY;
        if (damageSource.is(DamageTypes.ARROW)) {
            if (killer.getOffhandItem().is(ConventionalItemTags.RANGED_WEAPON_TOOLS)) weapon = killer.getOffhandItem();
            if (killer.getMainHandItem().is(ConventionalItemTags.RANGED_WEAPON_TOOLS)) weapon = killer.getMainHandItem();
        } else if (damageSource.is(DamageTypes.PLAYER_ATTACK) || damageSource.is(DamageTypes.MOB_ATTACK) || damageSource.is(DamageTypes.MOB_ATTACK_NO_AGGRO)) {
            weapon = killer.getMainHandItem();
        } else if (damageSource.is(DamageTypes.TRIDENT)) {
            weapon = ((ThrownTrident) damageSource.getDirectEntity()).getWeaponItem();
        }
        if (weapon.isEmpty()) return;

        EssorRevamped.LOGGER.info("Rewarding {} with experience.", weapon.getItemName().getString());

        Optional<ExperienceTable> experienceTable = ExperienceTableService.findExperienceTable(victim.level().getServer().getResourceManager(), weapon, ActionType.ENTITY_KILLING);
        if (experienceTable.isEmpty()) return;
        float experienceToGain = experienceTable.get().values().getOrDefault(BuiltInRegistries.ENTITY_TYPE.getKey(victim.getType()), 15.0f);

        ProgressionService.progressItem(weapon, experienceToGain);

        EssorRevamped.LOGGER.info("Rewarded {} with experience.", weapon.getItemName().getString());
    }
    // </editor-fold>
}
