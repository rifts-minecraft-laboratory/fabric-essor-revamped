package fr.noahboos.essorrevamped.network.payloads.definitions.progression;

import fr.noahboos.essorrevamped.EssorRevamped;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public record AddExperienceToastPayload(
    ItemStack itemStack,
    float experiencePointsGained
) implements CustomPacketPayload {
    public static final Type<AddExperienceToastPayload> TYPE = new Type<>(
        Identifier.fromNamespaceAndPath(EssorRevamped.MOD_ID, "payloads/add_experience_toast")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, AddExperienceToastPayload> STREAM_CODEC = StreamCodec.of(
        AddExperienceToastPayload::encode,
        AddExperienceToastPayload::decode
    );

    private static void encode(RegistryFriendlyByteBuf buffer, AddExperienceToastPayload payload) {
        ItemStack.STREAM_CODEC.encode(buffer, payload.itemStack);
        buffer.writeFloat(payload.experiencePointsGained);
    }

    private static AddExperienceToastPayload decode(RegistryFriendlyByteBuf buffer) {
        return new AddExperienceToastPayload(
            ItemStack.STREAM_CODEC.decode(buffer),
            buffer.readFloat()
        );
    }

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
