package com.example.databinding.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author: sq
 * @date ：2021/8/31
 * @Description: 自定义BindingAdapters
 */
public class AttributeUtil {

    /**
     * 定义单个属性
     *
     * @param imageView
     * @param url
     */
    @BindingAdapter("imageUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .into(imageView);
    }

    /**
     * 定义多个属性
     *
     * @param view
     * @param url
     * @param placeholder
     * @param error
     */
    @BindingAdapter(value = {"imageUrl", "placeholder", "error"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable placeholder, Drawable error) {
        RequestOptions options = new RequestOptions();
        options.placeholder(placeholder);
        options.error(error);
        Glide.with(view).load(url).apply(options).into(view);
    }

    @BindingAdapter("textContent")
    public static void setTextContent(TextView textView, String text) {
        String txt = text.toLowerCase();
        textView.setText(txt);
    }

}
