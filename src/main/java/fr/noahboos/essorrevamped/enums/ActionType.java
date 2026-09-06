package fr.noahboos.essorrevamped.enums;

public enum ActionType {
    BLOCK_BREAKING;

    public boolean is(ActionType other) {
        return this == other;
    }
}
