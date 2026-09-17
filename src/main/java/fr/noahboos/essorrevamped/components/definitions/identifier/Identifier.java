package fr.noahboos.essorrevamped.components.definitions.identifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.minecraft.core.UUIDUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public record Identifier(
    UUID uuid
) {
    // <editor-fold desc="Region - Codec and constructors." defaultstate="collapsed">
    public static final Codec<Identifier> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            UUIDUtil.CODEC.fieldOf("uuid").forGetter(Identifier::uuid)
        ).apply(instance, Identifier::new)
    );

    public Identifier {
        if (uuid == null) throw new IllegalArgumentException("uuid must be set.");
    }

    public Identifier() {
        this(UUID.randomUUID());
    }
    // </editor-fold>

    // <editor-fold desc="Region - Additional logic." defaultstate="collapsed">
    public static boolean isApplicableTo(Item item) {
        ItemStack itemStack = item.getDefaultInstance();

        return itemStack.is(EssorRevampedItemTagProvider.HAS_IDENTIFIER);
    }
    // </editor-fold>
}
