package fr.noahboos.essorrevamped.client.screens;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import javax.security.auth.callback.TextOutputCallback;

public class EssorRevampedConfigScreen extends Screen {
    private final Screen parent;

    public EssorRevampedConfigScreen(Screen parent) {
        super(Component.literal("Essor - Revamped: Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(
            Button.builder(Component.translatable("essor-revamped.screens.config.back"), button -> this.onClose())
                .bounds(centerX - 100, centerY + 20, 200, 20)
                .build()
        );
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(parent);
    }
}
