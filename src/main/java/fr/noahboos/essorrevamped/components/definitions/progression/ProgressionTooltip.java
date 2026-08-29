package fr.noahboos.essorrevamped.components.definitions.progression;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;

import java.util.function.Consumer;

public final class ProgressionTooltip {
    public static void tooltip(Progression progression, Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        tooltip.accept(Component.empty());
        tooltip.accept(Component.translatable("essor-revamped.components.progression.tooltip.experienceLevel", progression.experienceLevel(), progression.experiencePoints(), progression.experienceThreshold()));
        tooltip.accept(Component.literal(getExperienceLevelProgressionBar(progression)));
        tooltip.accept(Component.translatable("essor-revamped.components.progression.tooltip.masteryLevel", progression.masteryLevel(), getMasteryLevelProgressionBar(progression)));
        tooltip.accept(Component.translatable("essor-revamped.components.progression.tooltip.experienceMultiplier", progression.experienceMultiplier()));
    }

    public static String getExperienceLevelProgressionBar(Progression progression) {
        StringBuilder experienceLevelProgressBar = new StringBuilder();
        int experienceLevelProgressBarMaximumSegments = 25;
        double ratio = (double)progression.experiencePoints() / progression.experienceThreshold();
        int experienceLevelProgressBarFilledSegments = (int) Math.floor(Math.clamp(ratio, 0.0, 1.0) * experienceLevelProgressBarMaximumSegments);
        experienceLevelProgressBar.repeat("§6■", experienceLevelProgressBarFilledSegments);
        experienceLevelProgressBar.repeat("§7□", experienceLevelProgressBarMaximumSegments - experienceLevelProgressBarFilledSegments);
        return experienceLevelProgressBar.toString();
    }

    public static String getMasteryLevelProgressionBar(Progression progression) {
        StringBuilder masteryLevelProgressBar = new StringBuilder();
        masteryLevelProgressBar.repeat("§6★", Math.max(0, progression.masteryLevel()));
        masteryLevelProgressBar.repeat("§7☆", Math.max(0, progression.maximumMasteryLevel() - progression.masteryLevel()));
        return masteryLevelProgressBar.toString();
    }
}
