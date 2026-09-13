package fr.noahboos.essorrevamped.components.definitions.durability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record Durability(
    int durability
) implements TooltipProvider {
    public Durability {
        if (durability < 0) throw new IllegalArgumentException("durability must be equal to or greater than 0.");
    }

    public Durability() {
        this(0);
    }

    public static final int step = 1;

    public static final Codec<Durability> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.INT.fieldOf("durability").forGetter(Durability::durability)
        ).apply(instance, Durability::new)
    );

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        tooltip.accept(Component.empty());
        tooltip.accept(Component.translatable("essor-revamped.components.durability.tooltip", durability));
    }

    public static boolean isApplicableTo(Item item) {
        ItemStack itemStack = item.getDefaultInstance();

        return itemStack.is(EssorRevampedItemTagProvider.HAS_DURABILITY);
    }
}
