package fr.noahboos.essorrevamped.events.handlers.entity;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AllowDamageEventHandler {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, damageSource, damage) -> {
            AllowDamageEventHandler.handleWeapon(damageSource, damage);
            return true;
        });
    }

    // <editor-fold desc="Region - Damage source's weapon handling after damage has been taken." defaultstate="collapsed">
    public static void handleWeapon(DamageSource damageSource, float damage) {
        if (!(damageSource.getEntity() instanceof LivingEntity livingEntity)) return;
        EssorRevamped.LOGGER.info(damageSource.getEntity().getName().getString());

        ItemStack weapon = ItemStack.EMPTY;
        if (livingEntity.getOffhandItem().is(ConventionalItemTags.RANGED_WEAPON_TOOLS)) weapon = livingEntity.getOffhandItem();
        if (livingEntity.getMainHandItem().is(ConventionalItemTags.RANGED_WEAPON_TOOLS) || livingEntity.getMainHandItem().is(ConventionalItemTags.MELEE_WEAPON_TOOLS)) weapon = livingEntity.getWeaponItem();
        if (weapon.isEmpty()) return;

        EssorRevamped.LOGGER.info("Rewarding {} with experience.", weapon.getItemName().getString());

        float experienceToGain = (float) (damage * 3.0f);

        ProgressionService.progressItem(weapon, experienceToGain);

        EssorRevamped.LOGGER.info("Rewarded {} with experience.", weapon.getItemName().getString());
    }
    // </editor-fold>
}
