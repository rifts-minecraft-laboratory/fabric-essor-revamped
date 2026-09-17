package fr.noahboos.essorrevamped.network.payloads.definitions.progression;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public record AddExperienceLevelToastPayload(
    UUID uuid,
    ItemStack itemStack
) implements CustomPacketPayload {
    public static final Type<AddExperienceLevelToastPayload> TYPE = new Type<>(
        Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "payloads/add_experience_level_toast")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, AddExperienceLevelToastPayload> STREAM_CODEC = StreamCodec.of(
        AddExperienceLevelToastPayload::encode,
        AddExperienceLevelToastPayload::decode
    );

    private static void encode(RegistryFriendlyByteBuf buffer, AddExperienceLevelToastPayload payload) {
        buffer.writeUUID(payload.uuid());
        ItemStack.STREAM_CODEC.encode(buffer, payload.itemStack);
    }

    private static AddExperienceLevelToastPayload decode(RegistryFriendlyByteBuf buffer) {
        return new AddExperienceLevelToastPayload(
            buffer.readUUID(),
            ItemStack.STREAM_CODEC.decode(buffer)
        );
    }

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
