package fr.noahboos.essorrevamped.items;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class EssorRevampedItems {
    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(creativeModeTab -> {
            creativeModeTab.accept(EssorRevampedItems.MASTERY_LEVEL_UPGRADE_SMITHING_TEMPLATE);
        });
    }

    public static Item register(ResourceKey<Item> itemResourceKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemResourceKey));

        Registry.register(BuiltInRegistries.ITEM, itemResourceKey, item);

        return item;
    }

    public static final Item MASTERY_LEVEL_UPGRADE_SMITHING_TEMPLATE = register(
        EssorRevampedItemsIds.MASTERY_LEVEL_UPGRADE_SMITHING_TEMPLATE,
        Item::new,
        new Item.Properties()
    );
}
