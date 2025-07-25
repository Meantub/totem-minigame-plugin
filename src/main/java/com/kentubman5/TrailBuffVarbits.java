package com.kentubman5;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.gameval.VarbitID;

public class TrailBuffVarbits {
    public static ImmutableSet<Integer> TRAIL_BUFF_VARBIT_IDS = ImmutableSet.of(
            VarbitID.ENT_TOTEMS_SITE_1_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_2_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_3_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_4_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_5_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_6_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_7_TRAIL_BUFF_ACTIVE,
            VarbitID.ENT_TOTEMS_SITE_8_TRAIL_BUFF_ACTIVE);

    public static boolean isTrailBuffActiveVarbit(int varbitId) {
        return TRAIL_BUFF_VARBIT_IDS.contains(varbitId);
    }

    public static int getSiteFromVarbit(int varbitId) {
        if (varbitId == VarbitID.ENT_TOTEMS_SITE_1_TRAIL_BUFF_ACTIVE) {
            return 1;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_2_TRAIL_BUFF_ACTIVE) {
            return 2;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_3_TRAIL_BUFF_ACTIVE) {
            return 3;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_4_TRAIL_BUFF_ACTIVE) {
            return 4;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_5_TRAIL_BUFF_ACTIVE) {
            return 5;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_6_TRAIL_BUFF_ACTIVE) {
            return 6;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_7_TRAIL_BUFF_ACTIVE) {
            return 7;
        }
        else if (varbitId == VarbitID.ENT_TOTEMS_SITE_8_TRAIL_BUFF_ACTIVE) {
            return 8;
        }

        return -1;
    }
}
