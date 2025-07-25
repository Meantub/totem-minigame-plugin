package com.kentubman5.totem;

public class TotemAnimalNumber {
    public static int BUFFALO = 1;
    public static int JAGUAR = 2;
    public static int EAGLE = 3;
    public static int SNAKE = 4;
    public static int SCORPION = 5;

    public static String fromInt(int animalId) {
        if (animalId == BUFFALO) {
            return "Buffalo";
        }
        if (animalId == JAGUAR) {
            return "Jaguar";
        }
        if (animalId == EAGLE) {
            return "Eagle";
        }
        if (animalId == SNAKE) {
            return "Snake";
        }
        if (animalId == SCORPION) {
            return "Scorpion";
        }
        return "Unknown";
    }

    public static String friendlyString(int animalId) {
        return fromInt(animalId) + ("(" + animalId + ")");
    }

    public static String friendlyString(int animalId, boolean includeId) {
        return fromInt(animalId) + (includeId ? "(" + animalId + ")" : "");
    }
}
