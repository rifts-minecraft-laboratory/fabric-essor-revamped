package fr.noahboos.essorrevamped.components.definitions.progression;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record Progression(
    float experiencePoints,
    int experienceLevel,
    int masteryLevel,
    float experienceMultiplier
) implements TooltipProvider {
    public Progression {
        if (experienceLevel < 0) throw new IllegalArgumentException("experienceLevel must be equal to or greater than 0.");
        if (experiencePoints < 0) throw new IllegalArgumentException("experiencePoints must be equal to or greater than 0.");
        if (masteryLevel < 0) throw new IllegalArgumentException("masteryLevel must be equal to or greater than 0.");
        if (experienceMultiplier < 0) throw new IllegalArgumentException("experienceMultiplier must be equal to or greater than 0.");
    }

    public Progression() {
        this(0, 0, 0, 1.00f);
    }

    public int experienceThreshold() {
        return 100 * this.experienceLevel() + 100;
    }

    public int maximumMasteryLevel() {
        return 10;
    }

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

    public static final Codec<Progression> CODEC = RecordCodecBuilder.create(builder -> {
       return builder.group(
           Codec.FLOAT.fieldOf("experiencePoints").forGetter(Progression::experiencePoints),
           Codec.INT.fieldOf("experienceLevel").forGetter(Progression::experienceLevel),
           Codec.INT.fieldOf("masteryLevel").forGetter(Progression::masteryLevel),
           Codec.FLOAT.fieldOf("experienceMultiplier").forGetter(Progression::experienceMultiplier)
       ).apply(builder, Progression::new);
    });

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        ProgressionTooltip.tooltip(this, context, tooltip, flag, components);
    }

    public static boolean isApplicableTo(Item item) {
        ItemStack itemStack = item.getDefaultInstance();

        return itemStack.is(EssorRevampedItemTagProvider.HAS_PROGRESSION);
    }
}
