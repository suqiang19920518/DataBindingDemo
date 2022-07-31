package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.example.databinding.databinding.ActivityForFragmentBinding;
import com.example.databinding.databinding.LayoutTitleBarBinding;

public class ActivityForFragment extends AppCompatActivity {

    private ActivityForFragmentBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_for_fragment);

        mBinding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                LayoutTitleBarBinding viewStubBinding = DataBindingUtil.bind(inflated);
                viewStubBinding.setTitlebar("测试viewStub");
                //不生效，奇怪？？？
//                ResourceBean resourceBean = new ResourceBean();
//                resourceBean.setColor(getResources().getColor(R.color.design_default_color_secondary));
//                viewStubBinding.setResource(new ResourceBean());
                //不生效，奇怪？？？
//                viewStubBinding.tvTitleBar.setTextColor(getResources().getColor(R.color.black));
            }
        });

        ViewStub viewStub = mBinding.viewStub.getViewStub();
        View view = null;
        if (!mBinding.viewStub.isInflated()) {
            //注意：inflate后，再次调用mBinding.viewStub.getViewStub()，得到的viewStub为null
            view = viewStub.inflate();
        }
        LayoutTitleBarBinding viewStubBinding = DataBindingUtil.getBinding(view);

        //不生效，奇怪？？？
//        ResourceBean resourceBean = new ResourceBean();
//        resourceBean.setColor(getResources().getColor(R.color.design_default_color_secondary));
//        viewStubBinding.setResource(new ResourceBean());
        //生效
        viewStubBinding.tvTitleBar.setTextColor(getResources().getColor(R.color.black));
        viewStubBinding.setOnTitleBarOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.setVisibility(View.INVISIBLE);
            }
        });


        TestFragment testFragment = new TestFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, testFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }
}