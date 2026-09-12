package fr.noahboos.essorrevamped.components.definitions.progression;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import net.minecraft.world.item.ItemStack;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProgressionService {
    public static void progressItem(ItemStack itemStack, float experiencePoints) {
        Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);

        if (progression == null) {
            EssorRevamped.LOGGER.error("Cannot apply any progress to the given item ({}), as no Progression data component has been found on it.", itemStack.getItemName().getString());
            return;
        }

        Progression _progression = ProgressionService.gainExperiencePoints(progression, experiencePoints);

        itemStack.set(EssorRevampedComponents.PROGRESSION, _progression);
    }

    public static Progression gainExperiencePoints(Progression progression, float experiencePointsToGain) {
        float _experiencePointsToGain = experiencePointsToGain * progression.experienceMultiplier();
        if (progression.experienceLevel() == progression.experienceLevelThreshold()) _experiencePointsToGain *= 0.25f;

        float experiencePoints = BigDecimal.valueOf(progression.experiencePoints() + _experiencePointsToGain).setScale(3, RoundingMode.HALF_UP).floatValue();
        Progression _progression = progression.withExperiencePoints(experiencePoints);

        _progression = ProgressionService.levelUp(_progression);

        return _progression;
    }

    public static Progression levelUp(Progression progression) {
        Progression _progression = progression;

        while (_progression.experiencePoints() >= _progression.experiencePointThreshold() && _progression.experienceLevel() < _progression.experienceLevelThreshold()) {
            _progression = _progression.withExperiencePoints(_progression.experiencePoints() - _progression.experiencePointThreshold());
            _progression = _progression.withExperienceLevel(_progression.experienceLevel() + 1);
        }

        return _progression;
    }
}
