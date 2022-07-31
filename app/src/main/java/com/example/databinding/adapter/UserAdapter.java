package com.example.databinding.adapter;

import android.content.Context;

import com.example.databinding.R;
import com.example.databinding.databinding.UserItemBinding;
import com.example.databinding.field.User;

public class UserAdapter extends BaseBindingAdapter<User, UserItemBinding> {

    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.user_item;
    }

    @Override
    protected void onBindItem(UserItemBinding binding, User data) {
        binding.setUser(data);
    }
}
