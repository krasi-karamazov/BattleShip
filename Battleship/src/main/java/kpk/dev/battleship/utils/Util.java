package kpk.dev.battleship.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by krasimir.karamazov on 1/23/14.
 */
public class Util {

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
