package fr.noahboos.essorrevamped.components.definitions.progression;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

public record Progression(
    float experiencePoints,
    int experienceLevel,
    int masteryLevel,
    float experienceMultiplier
) implements TooltipProvider {
    // <editor-fold desc="Region - Codec and constructors." defaultstate="collapsed">
    public static final Codec<Progression> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
            Codec.FLOAT.fieldOf("experiencePoints").forGetter(Progression::experiencePoints),
            Codec.INT.fieldOf("experienceLevel").forGetter(Progression::experienceLevel),
            Codec.INT.fieldOf("masteryLevel").forGetter(Progression::masteryLevel),
            Codec.FLOAT.fieldOf("experienceMultiplier").forGetter(Progression::experienceMultiplier)
        ).apply(builder, Progression::new)
    );

    public Progression {
        if (experienceLevel < 0) throw new IllegalArgumentException("experienceLevel must be equal to or greater than 0.");
        if (experiencePoints < 0) throw new IllegalArgumentException("experiencePoints must be equal to or greater than 0.");
        if (masteryLevel < 0) throw new IllegalArgumentException("masteryLevel must be equal to or greater than 0.");
        if (experienceMultiplier < 0) throw new IllegalArgumentException("experienceMultiplier must be equal to or greater than 0.");
    }

    public Progression() {
        this(0, 0, 0, 1.00f);
    }
    // </editor-fold>

    // <editor-fold desc="Region - Additional getters and variables." defaultstate="collapsed">
    public int experiencePointThreshold() {
        return 100 * this.experienceLevel() + 100;
    }

    public int experienceLevelThreshold() {
        return Math.min(100 * this.masteryLevel() + 100, 1000);
    }

    public static int MAXIMUM_MASTERY_LEVEL = 10;

    public static float MAXIMUM_LEVEL_EXPERIENCE_MULTIPLIER = 0.25f;
    // </editor-fold>

    // <editor-fold desc="Region - Additional logic." defaultstate="collapsed">
    public boolean isExperienceLevelUpgradable() {
        return experiencePoints >= experiencePointThreshold()
            && experienceLevel < experienceLevelThreshold();
    }

    public boolean isExperienceLevelMaximised() {
        return experienceLevel == experienceLevelThreshold();
    }

    public boolean isMasteryLevelUpgradable() {
        return experienceLevel() >= experienceLevelThreshold() && masteryLevel() < MAXIMUM_MASTERY_LEVEL;
    }

    public static boolean isApplicableTo(Item item) {
        ItemStack itemStack = item.getDefaultInstance();

        return itemStack.is(EssorRevampedItemTagProvider.HAS_PROGRESSION);
    }
    // </editor-fold>

    // <editor-fold desc="Region - Mutation methods." defaultstate="collapsed">
    public Progression withExperiencePoints(float experiencePoints) {
        return new Progression(
            experiencePoints,
            this.experienceLevel,
            this.masteryLevel,
            this.experienceMultiplier
        );
    }

    public Progression withExperienceLevel(int experienceLevel) {
        return new Progression(
            this.experiencePoints,
            experienceLevel,
            this.masteryLevel,
            this.experienceMultiplier
        );
    }

    public Progression withMasteryLevel(int masteryLevel) {
        return new Progression(
            this.experiencePoints,
            this.experienceLevel,
            masteryLevel,
            this.experienceMultiplier
        );
    }

    public Progression withExperienceMultiplier(float experienceMultiplier) {
        return new Progression(
            this.experiencePoints,
            this.experienceLevel,
            this.masteryLevel,
            experienceMultiplier
        );
    }
    // </editor-fold>

    // <editor-fold desc="Region - Progression operations." defaultstate="collapsed">
    public Progression addExperiencePoints(float experiencePointsToAdd) {
        float _experiencePointsToAdd = experiencePointsToAdd * experienceMultiplier;
        if (isExperienceLevelMaximised()) _experiencePointsToAdd *= MAXIMUM_LEVEL_EXPERIENCE_MULTIPLIER;

        float totalExperiencePoints = BigDecimal
            .valueOf(experiencePoints + _experiencePointsToAdd)
            .setScale(3, RoundingMode.HALF_UP)
            .floatValue();

        return withExperiencePoints(totalExperiencePoints);
    }

    public Progression addExperienceLevels() {
        Progression _progression = this;

        while (_progression.isExperienceLevelUpgradable()) {
            _progression = _progression
                .withExperiencePoints(_progression.experiencePoints() - _progression.experiencePointThreshold())
                .withExperienceLevel(_progression.experienceLevel() + 1);
        }

        return _progression;
    }

    public Progression addMasteryLevel() {
        if (!isMasteryLevelUpgradable()) return this;

        int _masteryLevel = masteryLevel + 1;
        float _experienceMultiplier = BigDecimal
            .valueOf(1.00f + (_masteryLevel * 0.15f))
            .setScale(3, RoundingMode.HALF_UP)
            .floatValue();

        return withMasteryLevel(_masteryLevel)
            .withExperienceMultiplier(_experienceMultiplier)
            .addExperienceLevels();
    }
    // </editor-fold>

    // <editor-fold desc="Region - Tooltip related methods." defaultstate="collapsed">
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        ProgressionTooltip.tooltip(this, context, tooltip, flag, components);
    }
    // </editor-fold>
}
