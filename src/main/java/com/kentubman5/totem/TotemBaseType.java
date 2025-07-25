package com.kentubman5.totem;

public class TotemBaseType {
    public static int NONE = 0;
    public static int OAK = 1;
    public static int WILLOW = 2;
    public static int MAPLE = 3;
    public static int YEW = 4;
    public static int MAGIC = 5;
    public static int REDWOOD = 6;

    public static String fromInt(int baseId) {
        if (baseId == NONE) {
            return "None";
        }
        if (baseId == OAK) {
            return "Oak";
        }
        if (baseId == WILLOW) {
            return "Willow";
        }
        if (baseId == MAPLE) {
            return "Maple";
        }
        if (baseId == YEW) {
            return "Yew";
        }
        if (baseId == MAGIC) {
            return "Magic";
        }
        if (baseId == REDWOOD) {
            return "Redwood";
        }
        return "Unknown";
    }
}
