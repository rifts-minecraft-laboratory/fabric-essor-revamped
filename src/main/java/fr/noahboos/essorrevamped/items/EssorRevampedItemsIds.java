package fr.noahboos.essorrevamped.items;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class EssorRevampedItemsIds {
    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, name));
    }
}
