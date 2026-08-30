package fr.noahboos.essorrevamped.experiencetables;

import com.mojang.serialization.Codec;
import net.minecraft.resources.Identifier;

import java.util.Map;

public record ExperienceTable(
    Map<Identifier, Float> values
) {
    public static final Codec<ExperienceTable> CODEC = Codec.unboundedMap(
        Identifier.CODEC,
        Codec.FLOAT
    ).xmap(
        ExperienceTable::new,
        ExperienceTable::values
    );
}
