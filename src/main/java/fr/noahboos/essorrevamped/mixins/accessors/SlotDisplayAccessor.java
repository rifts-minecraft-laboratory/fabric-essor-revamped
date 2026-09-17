package fr.noahboos.essorrevamped.mixins.accessors;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.RandomSource;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;

@Mixin(SlotDisplay.class)
public interface SlotDisplayAccessor {
    @Invoker("applyDemoTransformation")
    static <T> Stream<T> applyDemoTransformation(final ContextMap context, final DisplayContentsFactory<T> factory, final SlotDisplay firstDisplay, final SlotDisplay secondDisplay, final RandomSource randomSource, final BinaryOperator<ItemStack> operation) {
        throw new AssertionError("Untransformed @Accessor");
    }
}