package fr.noahboos.essorrevamped.components.definitions.progression;

public class ProgressionService {
    public static Progression gainExperiencePoints(Progression progression, float experiencePoints) {
        Progression _progression = progression.withExperiencePoints(progression.experiencePoints() + experiencePoints);

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
