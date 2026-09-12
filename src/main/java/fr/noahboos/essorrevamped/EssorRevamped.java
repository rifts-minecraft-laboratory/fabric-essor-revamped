package fr.noahboos.essorrevamped;

import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.events.EssorRevampedEvents;
import fr.noahboos.essorrevamped.items.EssorRevampedItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EssorRevamped implements ModInitializer {
	public static final String MOD_ID = "essor-revamped";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		EssorRevampedComponents.initialize();
		EssorRevampedEvents.initialize();
		EssorRevampedItems.initialize();

		LOGGER.info("Hello Fabric world! Essor - Revamped is up. :D");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
