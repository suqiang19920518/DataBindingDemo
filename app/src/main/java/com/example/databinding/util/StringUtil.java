package com.example.databinding.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.databinding.R;

public class StringUtil {

    public static String toUpperCase(String content) {
        return content.toUpperCase();
    }

    public static String loadString(Context context) {
        return context.getResources().getString(R.string.tip);
    }
}
