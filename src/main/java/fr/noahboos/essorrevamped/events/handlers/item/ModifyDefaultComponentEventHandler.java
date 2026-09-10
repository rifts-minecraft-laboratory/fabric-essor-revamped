package fr.noahboos.essorrevamped.events.handlers.item;

import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

public class ModifyDefaultComponentEventHandler {
    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Progression::isApplicableTo , (builder, item) -> {
                builder.set(EssorRevampedComponents.PROGRESSION, new Progression());
            });
        });
    }
}
