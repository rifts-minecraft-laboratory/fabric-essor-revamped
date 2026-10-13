package fr.noahboos.essorrevamped.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public record EssorRevampedClientConfig(
    boolean displayAddExperiencePointsNotification,
    boolean displayAddExperienceLevelNotification
) {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("essor-revamped/client/config.json");

    public static EssorRevampedClientConfig defaults() {
        return new EssorRevampedClientConfig(
            true,
            true
        );
    }

    public static EssorRevampedClientConfig load() {
        if (!Files.exists(CONFIG_PATH)) {
            EssorRevampedClientConfig defaults = defaults();
            save(defaults);
            return defaults;
        }

        try {
            String json = Files.readString(CONFIG_PATH);
            EssorRevampedClientConfig loaded = GSON.fromJson(json, EssorRevampedClientConfig.class);
            return loaded != null ? loaded : defaults();
        } catch (Exception exception) {
            return defaults();
        }
    }

    public static void save(EssorRevampedClientConfig config) {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
        } catch (IOException ignored) {}
    }
}
