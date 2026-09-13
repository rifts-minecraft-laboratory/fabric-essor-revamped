package fr.noahboos.essorrevamped.client;

import fr.noahboos.essorrevamped.client.toasts.definitions.progression.AddExperienceToastClientReceiver;
import net.fabricmc.api.ClientModInitializer;

public class EssorRevampedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AddExperienceToastClientReceiver.initialize();
	}
}