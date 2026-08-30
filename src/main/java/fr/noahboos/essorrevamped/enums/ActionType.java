package fr.noahboos.essorrevamped.enums;

public enum ActionType {
    MINING;

    public boolean is(ActionType other) {
        return this == other;
    }
}
