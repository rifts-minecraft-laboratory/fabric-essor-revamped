package fr.noahboos.essorrevamped.client.toasts.definitions.progression;

import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperienceLevelToastPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AddExperienceLevelToastClientReceiver {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(AddExperienceLevelToastPayload.TYPE, ((payload, context) -> {
            context.client().execute(() -> {
                AddExperienceLevelToast.show(payload.uuid(), payload.itemStack());
            });
        }));
    }
}
