package com.dss886.dotaautochess.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dss886 on 2019/1/26.
 */
public class UIUtils {

    public static final int KEEP = -3;

    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        } else {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm == null ? 0 : dm.widthPixels;
        }
    }

    public static int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    public static void updateLayout(View view, int w, int h) {
        if (view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null && (params.width != w || params.height != h)) {
                if (w != KEEP) {
                    params.width = w;
                }
                if (h != KEEP) {
                    params.height = h;
                }
                view.setLayoutParams(params);
            }
        }
    }

    public static void updateLayoutMargin(View view, int l, int t, int r, int b) {
        if (view != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null) {
                if (params instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginParams = ((ViewGroup.MarginLayoutParams) params);
                    if (l != KEEP) {
                        marginParams.leftMargin = l;
                    }
                    if (t != KEEP) {
                        marginParams.topMargin = t;
                    }
                    if (r != KEEP) {
                        marginParams.rightMargin = r;
                    }
                    if (b != KEEP) {
                        marginParams.bottomMargin = b;
                    }
                    view.setLayoutParams(params);
                }
            }
        }
    }

    public static int getColorBrightness(int color) {
        return ((color & 0xff) * 19595
                + ((color >> 8) & 0xff) * 38469
                + ((color >> 16) & 0xff) * 7472) >> 16;
    }

    public static void setVisibility(View view, int visibility) {
        if (view != null && view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

}
