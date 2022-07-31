package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.adapter.UserAdapter;
import com.example.databinding.callback.OnRecyclerItemClickListener;
import com.example.databinding.databinding.ActivitySingleTypeBinding;
import com.example.databinding.field.User;

import java.util.List;

/**
 * 单类型item页面
 */
public class SingleTypeRecyclerViewActivity extends AppCompatActivity {

    private ActivitySingleTypeBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_type);
        initView();
    }

    private void initView() {
        UserAdapter bookAdapter = new UserAdapter(this);
        ObservableArrayList<User> users = new ObservableArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user;
            if (i == 5) {
                user = new User("小张" + i, "xiaozhang" + i, "深圳" + i, "165");
                user.setFlag(true);
            } else {
                user = new User("小张" + i, "xiaozhang" + i, "深圳" + i, "165");
                user.setFlag(false);
            }
            users.add(user);
        }
        bookAdapter.setData(users);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<User> data = bookAdapter.getData();
                if (position == 0) {
                    bookAdapter.removeItem(position);
                    Toast.makeText(SingleTypeRecyclerViewActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                } else if (position == 1) {
                    User user = new User("小王", "xiaowang", "广州", "170");
                    bookAdapter.setItem(position, user);
                    Toast.makeText(SingleTypeRecyclerViewActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                } else if (position == 2) {
                    User user = new User("小胡", "xiaohu", "东莞", "170");
                    bookAdapter.addItem(position, user);
                    Toast.makeText(SingleTypeRecyclerViewActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                } else if (position == 3) {
                    bookAdapter.clear();
                    Toast.makeText(SingleTypeRecyclerViewActivity.this, "清除成功", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setFlag(i == position);
                    }
                }

            }
        });
    }
}