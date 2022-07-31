package com.example.databinding.field;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.databinding.BR;
import com.example.databinding.R;
import com.example.databinding.callback.BindingAdapterItem;

public class Book extends BaseObservable implements BindingAdapterItem {
    private String title;
    private String author;
    private boolean isChecked;

    public Book(String title, String author, boolean isChecked) {
        this.title = title;
        this.author = author;
        this.isChecked = isChecked;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
    }

    @Bindable
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Override
    public int getViewType() {
        return R.layout.book_item;
    }

}
