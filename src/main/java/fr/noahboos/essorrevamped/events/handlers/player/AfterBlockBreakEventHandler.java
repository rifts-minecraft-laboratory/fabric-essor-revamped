package fr.noahboos.essorrevamped.events.handlers.player;

import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import fr.noahboos.essorrevamped.enums.ActionType;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTable;
import fr.noahboos.essorrevamped.experiencetables.ExperienceTableService;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Optional;

public class AfterBlockBreakEventHandler {
    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, blockPos, blockState, blockEntity) -> {
            Optional<ExperienceTable> experienceTable = ExperienceTableService.findExperienceTable(world.getServer().getResourceManager(), player.getMainHandItem(), ActionType.BLOCK_BREAKING);
            if (experienceTable.isEmpty()) return;
            float experienceToGain = experienceTable.get().values().getOrDefault(BuiltInRegistries.BLOCK.getKey(blockState.getBlock()), 0f);

            ProgressionService.progressItem(player.getMainHandItem(), experienceToGain);
        });
    }
}
