package fr.noahboos.essorrevamped.components;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EssorRevampedComponents {
    public static void initialize() {
        EssorRevamped.LOGGER.info("Registering {}'s data components.", EssorRevamped.MOD_ID);
        ItemComponentTooltipProviderRegistry.addFirst(EssorRevampedComponents.PROGRESSION);
        EssorRevamped.LOGGER.info("Registered {}'s data components.", EssorRevamped.MOD_ID);
    }

    public static final DataComponentType<Progression> PROGRESSION = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE,
        Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "components/progression"),
        DataComponentType.<Progression>builder().persistent(Progression.CODEC).build()
    );
}
