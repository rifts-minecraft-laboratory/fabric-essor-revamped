package fr.noahboos.essorrevamped.client.toasts.definitions.progression;

import fr.noahboos.essorrevamped.components.definitions.progression.ProgressionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class AddExperienceToast implements Toast {
    private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("toast/advancement");
    private Toast.Visibility wantedVisibility;
    private final ItemStack ITEM_STACK;
    private final Component TITLE;
    private final Component DESCRIPTION;

    public AddExperienceToast(ItemStack itemStack, Component title, Component description) {
        this.ITEM_STACK = itemStack;
        this.TITLE = title;
        this.DESCRIPTION = description;
    }

    @Override
    public Visibility getWantedVisibility() {
        return this.wantedVisibility;
    }

    @Override
    public void update(ToastManager manager, long fullyVisibleForMs) {
        this.wantedVisibility = (double)fullyVisibleForMs >= (double)5000.0F * manager.getNotificationDisplayTimeMultiplier() ? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, Font font, long fullyVisibleForMs) {
        graphics.blitSprite(
            RenderPipelines.GUI_TEXTURED,
            BACKGROUND_SPRITE,
            0,
            0,
            this.width(),
            this.height()
        );

        graphics.fakeItem(this.ITEM_STACK, 8, 8);
        graphics.text(Minecraft.getInstance().font, this.TITLE, 30, 7, 0xFFFFFFFF, false);
        graphics.text(Minecraft.getInstance().font, this.DESCRIPTION, 30, 18, 0xFFAAAAAA, false);
    }

    public static void show(ItemStack itemStack, float experiencePointsGained) {
        ProgressionService.getProgression(itemStack).ifPresent(progression -> {
            Minecraft.getInstance().gui.toastManager().addToast(new AddExperienceToast(
                itemStack,
                itemStack.getHoverName(),
                Component.translatable("essor-revamped.toasts.progression.addExperienceToast.description", experiencePointsGained, progression.experiencePoints(), progression.experiencePointThreshold())
            ));
        });
    }
}
