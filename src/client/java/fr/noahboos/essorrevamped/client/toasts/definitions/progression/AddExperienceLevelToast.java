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
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.UUID;

public class AddExperienceLevelToast implements Toast {
    private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("toast/advancement");
    private final Object TOKEN;
    private Toast.Visibility wantedVisibility;
    private final long lastUpdateTime;
    private final ItemStack ITEM_STACK;
    private final Component TITLE;
    private final Component DESCRIPTION;

    public AddExperienceLevelToast(UUID uuid, ItemStack itemStack, int experienceLevel) {
        this.TOKEN = uuid;
        this.lastUpdateTime = Util.getMillis();
        this.ITEM_STACK = itemStack;
        this.TITLE = Component.translatable("essor-revamped.toasts.progression.addExperienceLevelToast.title");
        this.DESCRIPTION = Component.translatable("essor-revamped.toasts.progression.addExperienceLevelToast.description", itemStack.getHoverName(), experienceLevel);
    }

    @Override
    public int width() {
        return 280;
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
        graphics.text(Minecraft.getInstance().font, this.DESCRIPTION, 30, 18, 0xFFAAAAAA, false);
        int maxWidth = this.width() - 30 - 8;

        List<FormattedCharSequence> lines = font.split(this.DESCRIPTION, maxWidth);

        for (int i = 0; i < lines.size(); i++) {
            graphics.text(
                font,
                lines.get(i),
                30,
                18 + i * 9,
                0xFFAAAAAA,
                false
            );
        }
    }

    @Override
    public @NonNull Object getToken() {
        return TOKEN;
    }

    public static void show(UUID uuid, ItemStack itemStack){
        ProgressionService.getProgression(itemStack).ifPresent(progression -> {
            ToastManager toastManager = Minecraft.getInstance().gui.toastManager();

            toastManager.addToast(new AddExperienceLevelToast(
                uuid,
                itemStack,
                progression.experienceLevel()
            ));
        });
    }
}
