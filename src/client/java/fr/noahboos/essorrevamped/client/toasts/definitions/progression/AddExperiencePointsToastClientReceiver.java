package fr.noahboos.essorrevamped.client.toasts.definitions.progression;

import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperiencePointsToastPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class AddExperiencePointsToastClientReceiver {
    public static void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(AddExperiencePointsToastPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> {
                AddExperiencePointsToast.show(payload.uuid(), payload.itemStack(), payload.experiencePointsGained());
            });
        });
    }
}
