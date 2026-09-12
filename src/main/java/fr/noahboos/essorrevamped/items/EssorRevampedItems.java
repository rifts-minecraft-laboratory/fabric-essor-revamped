package fr.noahboos.essorrevamped.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class EssorRevampedItems {
    public static void initialize() {
        //
    }

    public static Item register(ResourceKey<Item> itemResourceKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        Item item = itemFactory.apply(settings.setId(itemResourceKey));

        Registry.register(BuiltInRegistries.ITEM, itemResourceKey, item);

        return item;
    }
}
