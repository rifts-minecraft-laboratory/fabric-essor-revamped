package fr.noahboos.essorrevamped.client.screens;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import fr.noahboos.essorrevamped.client.config.EssorRevampedClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextRenderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import javax.security.auth.callback.TextOutputCallback;

public class EssorRevampedConfigScreen extends Screen {
    private final Screen parent;
    private boolean displayAddExperiencePointsNotification;
    private boolean displayAddExperienceLevelNotification;

    public EssorRevampedConfigScreen(Screen parent) {
        super(Component.literal("Essor - Revamped: Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        EssorRevampedClientConfig config = EssorRevampedClientConfig.load();
        this.displayAddExperiencePointsNotification = config.displayAddExperiencePointsNotification();
        this.displayAddExperienceLevelNotification = config.displayAddExperienceLevelNotification();

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int startX = 24;
        int endX = this.width - 24;
        int startY = 24;
        int endY = this.height - 24;

        this.addRenderableWidget(
            Button.builder(
                getYesOrNoComponent(displayAddExperiencePointsNotification),
                button -> {
                    displayAddExperiencePointsNotification = !displayAddExperiencePointsNotification;
                    button.setMessage(getYesOrNoComponent(displayAddExperiencePointsNotification));
                    saveConfig();
                }
            ).bounds(endX - 64, startY + (int) (this.font.lineHeight * 2.5f), 64, 20).build()
        );

        this.addRenderableWidget(
            Button.builder(
                getYesOrNoComponent(displayAddExperienceLevelNotification),
                button -> {
                    displayAddExperienceLevelNotification = !displayAddExperienceLevelNotification;
                    button.setMessage(getYesOrNoComponent(displayAddExperienceLevelNotification));
                    saveConfig();
                }
            ).bounds(endX - 64, startY + (int) (this.font.lineHeight * 4.5f), 64, 20).build()
        );

        this.addRenderableWidget(
            Button.builder(Component.translatable("essor-revamped.screens.config.back"), button -> this.onClose())
                .bounds(centerX - 100, endY - 20, 200, 20)
                .build()
        );
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int startX = 24;
        int endX = this.width - 24;
        int startY = 24;
        int endY = this.height - 24;

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // font, text, x, y, color, hasShadow
        graphics.text(
            this.font,
            Component.translatable("essor-revamped.screens.config.title"),
            centerX - (this.font.width(Component.translatable("essor-revamped.screens.config.title")) / 2),
            startY,
            0xFFFFFFFF,
            true
        );

        graphics.text(
            this.font,
            Component.translatable("essor-revamped.screens.config.displayAddExperiencePointsNotification"),
            startX,
            startY + this.font.lineHeight * 3,
            0xFFFFFFFF,
            true
        );

        graphics.text(
            this.font,
            Component.translatable("essor-revamped.screens.config.displayAddExperienceLevelNotification"),
            startX,
            startY + this.font.lineHeight * 5,
            0xFFFFFFFF,
            true
        );
    }

    private void saveConfig() {
        EssorRevampedClientConfig.save(
            new EssorRevampedClientConfig(
                this.displayAddExperiencePointsNotification,
                this.displayAddExperienceLevelNotification
            )
        );
    }

    private static Component getYesOrNoComponent(boolean value) {
        return value
            ? Component.translatable("essor-revamped.screens.config.yes")
            : Component.translatable("essor-revamped.screens.config.no");
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(parent);
    }
}
