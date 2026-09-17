package fr.noahboos.essorrevamped.components.definitions.durability;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import fr.noahboos.essorrevamped.tags.EssorRevampedItemTagProvider;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record Durability(
    int durabilityPoints
) implements TooltipProvider {
    // <editor-fold desc="Region - Codec and constructors." defaultstate="collapsed">
    public static final Codec<Durability> CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
            Codec.INT.fieldOf("durability").forGetter(Durability::durabilityPoints)
        ).apply(instance, Durability::new)
    );

    public Durability {
        if (durabilityPoints < 0) throw new IllegalArgumentException("durability must be equal to or greater than 0.");
    }

    public Durability() {
        this(0);
    }
    // </editor-fold>

    // <editor-fold desc="Region - Additional getters and variables." defaultstate="collapsed">
    public static final int STEP = 1;
    // </editor-fold>

    // <editor-fold desc="Region - Mutation methods." defaultstate="collapsed">
    public Durability withDurabilityPoints(int durabilityPoints) {
        return new Durability(durabilityPoints);
    }
    // </editor-fold>

    // <editor-fold desc="Durability operations" defaultstate="collapsed">
    public Durability addDurabilityPoints(int experienceLevel) {
        int totalDurabilityPoints = STEP * experienceLevel;

        return withDurabilityPoints(totalDurabilityPoints);
    }
    // </editor-fold>

    // <editor-fold desc="Region - Tooltip related methods." defaultstate="collapsed">
    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag flag, DataComponentGetter components) {
        tooltip.accept(Component.empty());
        tooltip.accept(Component.translatable("essor-revamped.components.durability.tooltip", durabilityPoints));
    }

    public static boolean isApplicableTo(Item item) {
        ItemStack itemStack = item.getDefaultInstance();

        return itemStack.is(EssorRevampedItemTagProvider.HAS_DURABILITY);
    }
    // </editor-fold>
}
