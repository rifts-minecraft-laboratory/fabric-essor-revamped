package fr.noahboos.essorrevamped.components.definitions.progression;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public final class ProgressionTooltip {
    public static void tooltip(Progression progression, Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        tooltip.accept(Component.empty());
        tooltip.accept(
            Component.translatable("essor-revamped.components.progression.tooltip.experienceLevel",
                Component.literal(String.valueOf(progression.experienceLevel())).withColor(TextColor.GOLD),
                Component.literal(String.valueOf(progression.experiencePoints())).withColor(TextColor.GOLD),
                Component.literal(String.valueOf(progression.experiencePointThreshold())).withColor(TextColor.GOLD)
            )
        );
        tooltip.accept(Component.literal(getExperienceLevelProgressionBar(progression)));
    }

    public static String getExperienceLevelProgressionBar(Progression progression) {
        StringBuilder experienceLevelProgressBar = new StringBuilder();
        int experienceLevelProgressBarMaximumSegments = 25;
        double ratio = (double)progression.experiencePoints() / progression.experiencePointThreshold();
        int experienceLevelProgressBarFilledSegments = (int) Math.floor(Math.clamp(ratio, 0.0, 1.0) * experienceLevelProgressBarMaximumSegments);
        experienceLevelProgressBar.repeat("§6■", experienceLevelProgressBarFilledSegments);
        experienceLevelProgressBar.repeat("§7□", experienceLevelProgressBarMaximumSegments - experienceLevelProgressBarFilledSegments);
        return experienceLevelProgressBar.toString();
    }
}
