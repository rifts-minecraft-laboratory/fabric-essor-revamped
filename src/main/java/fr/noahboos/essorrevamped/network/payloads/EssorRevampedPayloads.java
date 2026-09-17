package fr.noahboos.essorrevamped.network.payloads;

import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperienceToastPayload;
import fr.noahboos.essorrevamped.network.payloads.definitions.progression.AddExperienceLevelToastPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class EssorRevampedPayloads {
    public static void initialize() {
        PayloadTypeRegistry.clientboundPlay().register(
            AddExperienceToastPayload.TYPE,
            AddExperienceToastPayload.STREAM_CODEC
        );

        PayloadTypeRegistry.clientboundPlay().register(
            AddExperienceLevelToastPayload.TYPE,
            AddExperienceLevelToastPayload.STREAM_CODEC
        );
    }
}
