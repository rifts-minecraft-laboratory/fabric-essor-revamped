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
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public class AddExperienceToast implements Toast {
    private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("toast/advancement");
    private final Object TOKEN;
    private Toast.Visibility wantedVisibility;
    private long lastUpdateTime;
    private final ItemStack ITEM_STACK;
    private float experiencePointsGained;
    private final Component TITLE;
    private Component description;

    public AddExperienceToast(UUID uuid, ItemStack itemStack, float experiencePointsGained, Component title, Component description) {
        this.TOKEN = uuid;
        this.lastUpdateTime = Util.getMillis();
        this.ITEM_STACK = itemStack;
        this.experiencePointsGained = experiencePointsGained;
        this.TITLE = title;
        this.description = description;
    }

    public void resetLastUpdateTime() {
        this.lastUpdateTime = Util.getMillis();
    }

    public void addExperiencePointsGained(float experiencePointsGainedToAdd) {
        this.experiencePointsGained += experiencePointsGainedToAdd;
    }

    @Override
    public int width() {
        return 220;
    }

    @Override
    public Visibility getWantedVisibility() {
        return this.wantedVisibility;
    }

    @Override
    public void update(ToastManager manager, long fullyVisibleForMs) {
        long visibleFor = Util.getMillis() - this.lastUpdateTime;

        this.wantedVisibility = visibleFor >= 5000 * manager.getNotificationDisplayTimeMultiplier()
            ? Visibility.HIDE
            : Visibility.SHOW;
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
        graphics.text(Minecraft.getInstance().font, this.description, 30, 18, 0xFFAAAAAA, false);
    }

    @Override
    public @NonNull Object getToken() {
        return TOKEN;
    }

    public static void show(UUID uuid, ItemStack itemStack, float experiencePointsGained) {
        ProgressionService.getProgression(itemStack).ifPresent(progression -> {
            ToastManager toastManager = Minecraft.getInstance().gui.toastManager();

            if (toastManager.getToast(AddExperienceToast.class, uuid) instanceof AddExperienceToast toast) {
                toast.addExperiencePointsGained(experiencePointsGained);
                toast.resetLastUpdateTime();
                toast.description = Component.translatable("essor-revamped.toasts.progression.addExperienceToast.description", toast.experiencePointsGained, progression.experiencePoints(), progression.experiencePointThreshold());
            } else toastManager.addToast(new AddExperienceToast(
                uuid,
                itemStack,
                experiencePointsGained,
                itemStack.getHoverName(),
                Component.translatable("essor-revamped.toasts.progression.addExperienceToast.description", experiencePointsGained, progression.experiencePoints(), progression.experiencePointThreshold())
            ));
        });
    }
}
