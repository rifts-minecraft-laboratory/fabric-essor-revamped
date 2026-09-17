package fr.noahboos.essorrevamped.items.definitions;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class MasteryLevelUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component APPLIES_TO = Component.translatable(
        "item.essor-revamped.mastery_level_upgrade_smithing_template.applies_to"
    );

    private static final Component INGREDIENTS = Component.translatable(
        "item.essor-revamped.mastery_level_upgrade_smithing_template.ingredients"
    );

    private static final Component BASE_SLOT_DESCRIPTION = Component.translatable(
        "item.essor-revamped.mastery_level_upgrade_smithing_template.base_slot_description"
    );

    private static final Component ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
        "item.essor-revamped.mastery_level_upgrade_smithing_template.additions_slot_description"
    );

    private static final List<Identifier> BASE_SLOT_EMPTY_ICONS = List.of(
        Identifier.withDefaultNamespace("container/slot/sword"),
        Identifier.withDefaultNamespace("container/slot/spear"),
        Identifier.withDefaultNamespace("container/slot/shovel"),
        Identifier.withDefaultNamespace("container/slot/shield"),
        Identifier.withDefaultNamespace("container/slot/pickaxe"),
        Identifier.withDefaultNamespace("container/slot/leggings"),
        Identifier.withDefaultNamespace("container/slot/hoe"),
        Identifier.withDefaultNamespace("container/slot/helmet"),
        Identifier.withDefaultNamespace("container/slot/chestplate"),
        Identifier.withDefaultNamespace("container/slot/boots"),
        Identifier.withDefaultNamespace("container/slot/axe")
    );

    private static final List<Identifier> ADDITIONS_SLOT_EMPTY_ICONS = List.of(
        Identifier.withDefaultNamespace("container/slot/amethyst_shard")
    );

    public MasteryLevelUpgradeSmithingTemplateItem(Item.Properties properties) {
        super(
            APPLIES_TO,
            INGREDIENTS,
            BASE_SLOT_DESCRIPTION,
            ADDITIONS_SLOT_DESCRIPTION,
            BASE_SLOT_EMPTY_ICONS,
            ADDITIONS_SLOT_EMPTY_ICONS,
            properties
        );
    }
}