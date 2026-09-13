package fr.noahboos.essorrevamped.recipes.definitions.progression.masterylevel;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essorrevamped.components.EssorRevampedComponents;
import fr.noahboos.essorrevamped.components.definitions.progression.Progression;
import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SmithingRecipeDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class MasteryLevelSmithingRecipe extends SimpleSmithingRecipe {
    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Optional<Ingredient> addition;

    public MasteryLevelSmithingRecipe(final Recipe.CommonInfo commonInfo, final Optional<Ingredient> template, final Ingredient base, final Optional<Ingredient> addition) {
        super(commonInfo);
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    public static final MapCodec<MasteryLevelSmithingRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            CommonInfo.MAP_CODEC.forGetter(object -> object.commonInfo),
            Ingredient.CODEC.optionalFieldOf("template").forGetter(recipe -> recipe.template),
            Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
            Ingredient.CODEC.optionalFieldOf("addition").forGetter(recipe -> recipe.addition)
        ).apply(instance, MasteryLevelSmithingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, MasteryLevelSmithingRecipe> STREAM_CODEC = StreamCodec.composite(
        CommonInfo.STREAM_CODEC, recipe -> recipe.commonInfo,
        Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, recipe -> recipe.template,
        Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.base,
        Ingredient.OPTIONAL_CONTENTS_STREAM_CODEC, recipe -> recipe.addition,
        MasteryLevelSmithingRecipe::new
    );

    public static final RecipeSerializer<MasteryLevelSmithingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(@NonNull SmithingRecipeInput smithingRecipeInput, @NonNull Level level) {
        ItemStack baseStack = smithingRecipeInput.base();
        Progression progression = baseStack.get(EssorRevampedComponents.PROGRESSION);

        return this.template.map(ingredient -> ingredient.test(smithingRecipeInput.template())).orElse(true)
            && this.base.test(baseStack)
            && this.addition.map(ingredient -> ingredient.test(smithingRecipeInput.addition())).orElse(true)
            && progression != null
            && progression.isMasteryLevelUpgradable();
    }

    @Override
    public @NonNull ItemStack assemble(SmithingRecipeInput smithingRecipeInput) {
        return applyProgress(smithingRecipeInput.base().copy());
    }

    public static ItemStack applyProgress(ItemStack itemStack) {
        Progression progression = itemStack.get(EssorRevampedComponents.PROGRESSION);
        if (progression == null) return itemStack;
        progression = ProgressionService.masteryUp(progression);

        itemStack.set(EssorRevampedComponents.PROGRESSION, progression);

        return itemStack;
    }

    @Override
    public @NonNull RecipeSerializer<? extends SimpleSmithingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NonNull Optional<Ingredient> templateIngredient() {
        return this.template;
    }

    @Override
    public @NonNull Ingredient baseIngredient() {
        return this.base;
    }

    @Override
    public @NonNull Optional<Ingredient> additionIngredient() {
        return this.addition;
    }

    @Override
    protected @NonNull PlacementInfo createPlacementInfo() {
        return PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), this.addition));
    }

    @Override
    public @NonNull List<RecipeDisplay> display() {
        SlotDisplay template = Ingredient.optionalIngredientToDisplay(this.template);
        SlotDisplay base = this.base.display();
        SlotDisplay material = Ingredient.optionalIngredientToDisplay(this.addition);

        return List.of(new SmithingRecipeDisplay(
            template,
            base,
            material,
            new MasteryLevelSmithingDemoSlotDisplay(base, material),
            new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)
        ));
    }
}
