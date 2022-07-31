package com.example.databinding;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.field.ResourceBean;
import com.example.databinding.field.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取 <data>标签对象，ActivityMainBinding 类是自动生成的
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //创建User对象
        user = new User("小王", "XiaoWang", "深圳市", "165cm");
        user.setUrl("https://www.baidu.com/img/bdlogo.png");
        //绑定user到布局对象中
        mBinding.setUser(user);
        mBinding.setTitle("通过id无需自己创建控件");
        //所有的 set 方法也是根据布局中 variable 名称生成的
        mBinding.setResult("提交成功");
        //注意这里千万不要绑定具体的值
        mBinding.setMain(this);
        ResourceBean resourceBean = new ResourceBean();
        resourceBean.setColor(getResources().getColor(R.color.design_default_color_secondary));
        mBinding.setResource(resourceBean);
        initView();
    }

    private void initView() {
        mBinding.btnSubmit.setText("提交");
        mBinding.titleBarInclude.tvTitleBar.setTextColor(getResources().getColor(R.color.design_default_color_error));
        mBinding.titleBarInclude.tvTitleBar.setGravity(Gravity.CENTER);
        mBinding.tvName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, user.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void simpleOnClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_user_address) {
            mBinding.tvName.setText("rrrr");
            Toast.makeText(this, "address被点击", Toast.LENGTH_LONG).show();
            user.setAddress("广东省");
        } else if (id == R.id.tv_user_height) {
            Toast.makeText(this, "height被点击", Toast.LENGTH_LONG).show();
            user.setHeight("170cm");
        } else if (id == R.id.tv_title_bar) {
            Toast.makeText(this, "title_bar被点击", Toast.LENGTH_LONG).show();
        } else if (id == R.id.btn_to_single_list) {
            startActivity(new Intent(this, SingleTypeRecyclerViewActivity.class));
        } else if (id == R.id.btn_to_varied_list) {
            startActivity(new Intent(this, MultiTypeRecyclerViewActivity.class));
        } else if (id == R.id.btn_to_viewpager) {
            startActivity(new Intent(this, ViewPagerActivity.class));
        }
    }

    public void submit(boolean result) {
        Toast.makeText(this, result + "", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, ActivityForFragment.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }
}