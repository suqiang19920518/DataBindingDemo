package com.example.databinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ActivityViewPagerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager);
        initView();
    }

    private void initView() {
        List<View> mCards = new ArrayList<>();
        View restartView = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_restart, null, false).getRoot();
        View shutdownView = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.fragment_shutdown, null, false).getRoot();
        mCards.add(restartView);
        mCards.add(shutdownView);
        mBinding.idvShutdown.setCnt(mCards.size());
        ShutdownDialogAdapter mAdapter = new ShutdownDialogAdapter(mCards, this);
        mBinding.vpShutdown.setAdapter(mAdapter);
        mBinding.vpShutdown.setOffscreenPageLimit(mCards.size());
        mBinding.vpShutdown.addOnPageChangeListener(mPageChangeListener);
    }

    public void onItemClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_shutdown_icon) {
            Toast.makeText(this, "关机被点击", Toast.LENGTH_LONG).show();
        } else if (id == R.id.iv_restart_icon) {
            Toast.makeText(this, "重启被点击", Toast.LENGTH_LONG).show();
        } else if (id == R.id.restart_back || id == R.id.power_off_back) {
            finish();
            Toast.makeText(this, "返回被点击", Toast.LENGTH_LONG).show();
        }
    }

    private final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mBinding.idvShutdown != null) {
                mBinding.idvShutdown.setPosition(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public static class ShutdownDialogAdapter extends PagerAdapter {

        private List<View> mList;
        private ViewPagerActivity activity;

        public ShutdownDialogAdapter(List<View> mList, ViewPagerActivity activity) {
            this.mList = mList;
            this.activity = activity;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position);
            ViewDataBinding dataBinding = DataBindingUtil.bind(view);
            dataBinding.setVariable(BR.activity, activity);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }
    }
}