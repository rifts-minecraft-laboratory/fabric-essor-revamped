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
    int experienceLevel
) implements TooltipProvider {
    // <editor-fold desc="Region - Codec and constructors." defaultstate="collapsed">
    public static final Codec<Progression> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
            Codec.FLOAT.fieldOf("experiencePoints").forGetter(Progression::experiencePoints),
            Codec.INT.fieldOf("experienceLevel").forGetter(Progression::experienceLevel)
        ).apply(builder, Progression::new)
    );

    public Progression {
        if (experienceLevel < 0) throw new IllegalArgumentException("experienceLevel must be equal to or greater than 0.");
        if (experiencePoints < 0) throw new IllegalArgumentException("experiencePoints must be equal to or greater than 0.");
    }

    public Progression() {
        this(0, 0);
    }
    // </editor-fold>

    // <editor-fold desc="Region - Additional getters and variables." defaultstate="collapsed">
    public int experiencePointThreshold() {
        return 25 * this.experienceLevel() + 25;
    }

    public int experienceLevelThreshold() {
        return 1000;
    }

    public static int MAXIMUM_MASTERY_LEVEL = 10;
    // </editor-fold>

    // <editor-fold desc="Region - Additional logic." defaultstate="collapsed">
    public boolean isExperienceLevelUpgradable() {
        return experiencePoints >= experiencePointThreshold()
            && experienceLevel < experienceLevelThreshold();
    }

    public boolean isExperienceLevelMaximised() {
        return experienceLevel == experienceLevelThreshold();
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
            this.experienceLevel
        );
    }

    public Progression withExperienceLevel(int experienceLevel) {
        return new Progression(
            this.experiencePoints,
            experienceLevel
        );
    }
    // </editor-fold>

    // <editor-fold desc="Region - Progression operations." defaultstate="collapsed">
    public Progression addExperiencePoints(float experiencePointsToAdd) {
        float _experiencePoints = BigDecimal
            .valueOf(experiencePoints + experiencePointsToAdd)
            .setScale(3, RoundingMode.HALF_UP)
            .floatValue();

        return withExperiencePoints(_experiencePoints);
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
    // </editor-fold>

    // <editor-fold desc="Region - Tooltip related methods." defaultstate="collapsed">
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        ProgressionTooltip.tooltip(this, context, tooltip, flag, components);
    }
    // </editor-fold>
}
