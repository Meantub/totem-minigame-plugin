package com.kentubman5.totem;

import com.google.common.collect.ImmutableMap;
import net.runelite.api.coords.WorldPoint;

import java.util.Map;

public class TotemLocations {
    // NORTH WEST
    public static WorldPoint TOTEM_1 = new WorldPoint(1370, 3375, 0);
    public static WorldPoint TOTEM_1_OFFERINGS = new WorldPoint(1370, 3374, 0);

    // WEST
    public static WorldPoint TOTEM_2 = new WorldPoint(1346, 3319, 0);
    public static WorldPoint TOTEM_2_OFFERINGS = new WorldPoint(1347, 3319, 0);

    // SOUTH
    public static WorldPoint TOTEM_3 = new WorldPoint(1385, 3274, 0);
    public static WorldPoint TOTEM_3_OFFERINGS = new WorldPoint(1385, 3275, 0);

    // SOUTH EAST NEXT TO TREE STUMP
    public static WorldPoint TOTEM_4 = new WorldPoint(1413, 3286, 0);
    public static WorldPoint TOTEM_4_OFFERINGS = new WorldPoint(1412, 3286, 0);

    // SOUTH EAST TREE STUMP
    public static WorldPoint TOTEM_5 = new WorldPoint(1438, 3305, 0);
    public static WorldPoint TOTEM_5_OFFERINGS = new WorldPoint(1438, 3306, 0);

    // EAST
    public static WorldPoint TOTEM_6 = new WorldPoint(1477, 3332, 0);
    public static WorldPoint TOTEM_6_OFFERINGS = new WorldPoint(1478, 3332, 0);

    // NORTH EAST
    public static WorldPoint TOTEM_7 = new WorldPoint(1453, 3341, 0);
    public static WorldPoint TOTEM_7_OFFERINGS = new WorldPoint(1452, 3341, 0);

    // CENTER
    public static WorldPoint TOTEM_8 = new WorldPoint(1398, 3329, 0);
    public static WorldPoint TOTEM_8_OFFERINGS = new WorldPoint(1398, 3330, 0);

    public static Map<Integer, WorldPoint> TOTEM_MAP = ImmutableMap.<Integer, WorldPoint>builder()
            .put(1, TOTEM_1)
            .put(2, TOTEM_2)
            .put(3, TOTEM_3)
            .put(4, TOTEM_4)
            .put(5, TOTEM_5)
            .put(6, TOTEM_6)
            .put(7, TOTEM_7)
            .put(8, TOTEM_8)
            .build();

    public static Map<Integer, WorldPoint> TOTEM_OFFERINGS_MAP = ImmutableMap.<Integer, WorldPoint>builder()
            .put(1, TOTEM_1_OFFERINGS)
            .put(2, TOTEM_2_OFFERINGS)
            .put(3, TOTEM_3_OFFERINGS)
            .put(4, TOTEM_4_OFFERINGS)
            .put(5, TOTEM_5_OFFERINGS)
            .put(6, TOTEM_6_OFFERINGS)
            .put(7, TOTEM_7_OFFERINGS)
            .put(8, TOTEM_8_OFFERINGS)
            .build();
}
