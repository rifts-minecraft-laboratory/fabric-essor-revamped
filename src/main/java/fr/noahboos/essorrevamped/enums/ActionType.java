package fr.noahboos.essorrevamped.enums;

public enum ActionType {
    BLOCK_BREAKING,
    ENTITY_KILLING;

    public boolean is(ActionType other) {
        return this == other;
    }
}
