package fr.noahboos.essorrevamped.recipes.definitions.progression.masterylevel;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.mixins.accessors.SlotDisplayAccessor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public record MasteryLevelSmithingDemoSlotDisplay(SlotDisplay base, SlotDisplay material) implements SlotDisplay {
    public static final MapCodec<MasteryLevelSmithingDemoSlotDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            SlotDisplay.CODEC.fieldOf("base").forGetter(display -> display.base),
            SlotDisplay.CODEC.fieldOf("material").forGetter(display -> display.material)
        ).apply(instance, MasteryLevelSmithingDemoSlotDisplay::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, MasteryLevelSmithingDemoSlotDisplay> STREAM_CODEC = StreamCodec.composite(
        SlotDisplay.STREAM_CODEC, display -> display.base,
        SlotDisplay.STREAM_CODEC, display -> display.material,
        MasteryLevelSmithingDemoSlotDisplay::new
    );

    public static final Type<MasteryLevelSmithingDemoSlotDisplay> TYPE = new Type<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public <T> @NonNull Stream<T> resolve(ContextMap contextMap, DisplayContentsFactory<T> displayContentsFactory) {
        RandomSource randomSource = RandomSource.createThreadLocalInstance(System.identityHashCode(this));
        BinaryOperator<ItemStack> transformation = (base, material) -> MasteryLevelSmithingRecipe.applyProgress(base);
        return SlotDisplayAccessor.applyDemoTransformation(contextMap, displayContentsFactory, this.base, this.material, randomSource, transformation);
    }

    @Override
    public @NonNull Type<? extends SlotDisplay> type() {
        return TYPE;
    }
}
