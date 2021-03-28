package com.crTech.prakashmarble.ui.Common;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.WindowManager;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    //====================================================//
    public static boolean isNumberValid(String mobile) {
        boolean isValid = false;
        String expression = "^[0-9]{10}$";
        CharSequence inputStr = mobile;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
//================================================================//
    @SuppressWarnings("unused")
    public static String getmiliTimeStamp() {
        long LIMIT = 10000000000L;
        long t = Calendar.getInstance().getTimeInMillis();
        return String.valueOf(t).substring(0, 10);
    }
    // ========================================================================//
    @SuppressWarnings("unused")
    public static int getScaleedImage(Bitmap bitmap, Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int i = display.getHeight();
        int j = display.getWidth();
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        float scale = (float) j / originalWidth;
        int newHeight = (int) Math.round(originalHeight * scale);
        return newHeight;
    }
    // ==================================================================//
    public static boolean isNetworkConnectedMainThred(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null)
            return false;
        else
            return true;
    }
}
