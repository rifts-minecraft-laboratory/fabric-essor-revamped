package fr.noahboos.essorrevamped.components.definitions.progression;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProgressionService {
    public static Progression gainExperiencePoints(Progression progression, float experiencePoints) {
        float _experiencePoints = BigDecimal.valueOf(progression.experiencePoints() + experiencePoints).setScale(3, RoundingMode.HALF_UP).floatValue();
        Progression _progression = progression.withExperiencePoints(_experiencePoints);

        _progression = ProgressionService.levelUp(_progression);

        return _progression;
    }

    public static Progression levelUp(Progression progression) {
        Progression _progression = progression;

        while (_progression.experiencePoints() > _progression.experienceThreshold()) {
            _progression = _progression.withExperiencePoints(_progression.experiencePoints() - _progression.experienceThreshold());
            _progression = _progression.withExperienceLevel(_progression.experienceLevel() + 1);
        }

        return _progression;
    }
}
