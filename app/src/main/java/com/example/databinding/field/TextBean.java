package com.example.databinding.field;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.databinding.BR;
import com.example.databinding.R;
import com.example.databinding.callback.BindingAdapterItem;

public class TextBean extends BaseObservable implements BindingAdapterItem {

    private String text;

    public TextBean(String text) {
        this.text = text;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    @Override
    public int getViewType() {
        return R.layout.text_item;
    }
}
