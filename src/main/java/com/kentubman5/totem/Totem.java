package com.kentubman5.totem;

import com.kentubman5.TrailBuffVarbits;
import net.runelite.api.gameval.VarbitID;

public class Totem {
    public int siteNumber;

    public int base = 0;
    public int baseCarved = 0;
    public int baseMultiLoc = 0;
    public int low = 0;
    public int mid = 0;
    public int top = 0;
    public int decorations = 0;
    public int animal1 = 0;
    public int animal2 = 0;
    public int animal3 = 0;
    public int decay = 0;
    public int points = 0;
    public int multiAnimalA1 = 0;
    public int multiAnimalB1 = 0;
    public int multiAnimalC1 = 0;
    public int multiAnimalD1 = 0;
    public int multiAnimalE1 = 0;
    public int allMultiAnimals = 0;

    public boolean isTrailBuffActive = false;

    private final static int ENT_VARBIT_COUNT = (VarbitID.ENT_TOTEMS_SITE_1_ALL_MULTIANIMALS - VarbitID.ENT_TOTEMS_SITE_1_BASE) + 1;

    public Totem(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    private static class RelativeTotemVarbit {
        public static final int BASE = 0;
        public static final int BASE_CARVED = 1;
        public static final int BASE_MULTILOC = 2;
        public static final int LOW = 3;
        public static final int MID = 4;
        public static final int TOP = 5;
        public static final int DECORATIONS = 6;
        public static final int ANIMAL_1 = 7;
        public static final int ANIMAL_2 = 8;
        public static final int ANIMAL_3 = 9;
        public static final int DECAY = 10;
        public static final int POINTS = 11;
        public static final int MULTIANIMAL_A_1 = 12;
        public static final int MULTIANIMAL_B_1 = 13;
        public static final int MULTIANIMAL_C_1 = 14;
        public static final int MULTIANIMAL_D_1 = 15;
        public static final int MULTIANIMAL_E_1 = 16;
        public static final int ALL_MULTIANIMALS = 17;

        public static int fromGlobalVarbit(int varbitId) {
            return (varbitId - VarbitID.ENT_TOTEMS_SITE_1_BASE) % ENT_VARBIT_COUNT;
        }
    }

    public void setBasedOnVarbit(int varbitId, int varbitValue) {
        var relativeVarbit = RelativeTotemVarbit.fromGlobalVarbit(varbitId);

        switch (relativeVarbit) {
            case RelativeTotemVarbit.BASE:
                base = varbitValue;
                break;
            case RelativeTotemVarbit.BASE_CARVED:
                baseCarved = varbitValue;
                break;
            case RelativeTotemVarbit.BASE_MULTILOC:
                baseMultiLoc = varbitValue;
                break;
            case RelativeTotemVarbit.LOW:
                low = varbitValue;
                break;
            case RelativeTotemVarbit.MID:
                mid = varbitValue;
                break;
            case RelativeTotemVarbit.TOP:
                top = varbitValue;
                break;
            case RelativeTotemVarbit.DECORATIONS:
                decorations = varbitValue;
                break;
            case RelativeTotemVarbit.ANIMAL_1:
                animal1 = varbitValue;
                break;
            case RelativeTotemVarbit.ANIMAL_2:
                animal2 = varbitValue;
                break;
            case RelativeTotemVarbit.ANIMAL_3:
                animal3 = varbitValue;
                break;
            case RelativeTotemVarbit.DECAY:
                decay = varbitValue;
                break;
            case RelativeTotemVarbit.POINTS:
                points = varbitValue;
                break;
            case RelativeTotemVarbit.MULTIANIMAL_A_1:
                multiAnimalA1 = varbitValue;
                break;
            case RelativeTotemVarbit.MULTIANIMAL_B_1:
                multiAnimalB1 = varbitValue;
                break;
            case RelativeTotemVarbit.MULTIANIMAL_C_1:
                multiAnimalC1 = varbitValue;
                break;
            case RelativeTotemVarbit.MULTIANIMAL_D_1:
                multiAnimalD1 = varbitValue;
                break;
            case RelativeTotemVarbit.MULTIANIMAL_E_1:
                multiAnimalE1 = varbitValue;
                break;
            case RelativeTotemVarbit.ALL_MULTIANIMALS:
                allMultiAnimals = varbitValue;
                break;
        }
    }

    public static int identifySiteFromVarbit(int varbitId) {
        if (TrailBuffVarbits.isTrailBuffActiveVarbit(varbitId)) {
            return TrailBuffVarbits.getSiteFromVarbit(varbitId);
        }

        if (varbitId < VarbitID.ENT_TOTEMS_SITE_1_BASE || varbitId > VarbitID.ENT_TOTEMS_SITE_8_ALL_MULTIANIMALS) {
            return -1;
        }

        return (varbitId - VarbitID.ENT_TOTEMS_SITE_1_BASE) / ENT_VARBIT_COUNT;
    }

    @Override
    public String toString() {
        return "Totem{" +
                "siteNumber=" + siteNumber +
                ", base=" + TotemBaseType.fromInt(base) +
                ", baseCarved=" + baseCarved +
                ", baseMultiLoc=" + baseMultiLoc +
                ", low=" + low +
                ", mid=" + mid +
                ", top=" + top +
                ", decorations=" + decorations +
                ", animal1=" + TotemAnimalNumber.fromInt(animal1) +
                ", animal2=" + TotemAnimalNumber.fromInt(animal2) +
                ", animal3=" + TotemAnimalNumber.fromInt(animal3) +
                ", decay=" + decay +
                ", points=" + points +
                ", multiAnimalA1=" + multiAnimalA1 +
                ", multiAnimalB1=" + multiAnimalB1 +
                ", multiAnimalC1=" + multiAnimalC1 +
                ", multiAnimalD1=" + multiAnimalD1 +
                ", multiAnimalE1=" + multiAnimalE1 +
                ", allMultiAnimals=" + allMultiAnimals +
                ", isTotemTrailBuffActive=" + isTrailBuffActive +
                '}';
    }
}
