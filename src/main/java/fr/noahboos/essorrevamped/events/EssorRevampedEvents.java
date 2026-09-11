package fr.noahboos.essorrevamped.events;

import fr.noahboos.essorrevamped.EssorRevamped;
import fr.noahboos.essorrevamped.events.handlers.entity.AfterDamageEventHandler;
import fr.noahboos.essorrevamped.events.handlers.entity.AllowDamageEventHandler;
import fr.noahboos.essorrevamped.events.handlers.item.ModifyDefaultComponentEventHandler;
import fr.noahboos.essorrevamped.events.handlers.player.AfterBlockBreakEventHandler;

public class EssorRevampedEvents {
    public static void initialize() {
        EssorRevamped.LOGGER.info("Registering {}'s events.", EssorRevamped.MOD_ID);
        ModifyDefaultComponentEventHandler.register();
        AfterBlockBreakEventHandler.register();
        AfterDamageEventHandler.register();
        AllowDamageEventHandler.register();
        EssorRevamped.LOGGER.info("Registered {}'s events.", EssorRevamped.MOD_ID);
    }
}
