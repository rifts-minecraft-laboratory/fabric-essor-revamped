package fr.noahboos.essorrevamped.client.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.noahboos.essorrevamped.client.screens.EssorRevampedConfigScreen;

public class EssorRevampedModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return EssorRevampedConfigScreen::new;
    }
}
