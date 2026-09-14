package fr.noahboos.essorrevamped.client.toasts.definitions.progression;

import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperienceToastPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AddExperienceToastClientReceiver {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(AddExperienceToastPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> {
                AddExperienceToast.show(payload.uuid(), payload.itemStack(), payload.experiencePointsGained());
            });
        });
    }
}
