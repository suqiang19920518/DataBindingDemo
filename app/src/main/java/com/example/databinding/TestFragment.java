package com.example.databinding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableArrayMap;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.databinding.databinding.FragmentTestBinding;
import com.example.databinding.field.ObservableFieldContact;

/**
 * 有三种不同的动态更新数据的机制：
 * <p>
 * Observable 对象--------->BaseObservable
 * Observable 字段--------->ObservableFields
 * Observable 容器类-------->ObservableArrayMap、ObservableArrayList
 */
public class TestFragment extends Fragment {

    private ObservableFieldContact contact;
    private ObservableArrayMap<String, String> user;
    private ObservableArrayList<String> books;
    private FragmentTestBinding mBinding;

    public TestFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = new ObservableFieldContact("张三", "13584987421", 1);
        user = new ObservableArrayMap<>();
        user.put("firstname", "zhang");
        user.put("lastname", "san");
        user.put("age", "22");
        books = new ObservableArrayList<>();
        books.add("西游记");
        books.add("水浒传");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 通过DataBindingUtil.inflate初始化binding对象
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        mBinding.setContact(contact);
        mBinding.setUser(user);
        mBinding.setBooks(books);
        mBinding.setFragment(this);
        mBinding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getContext(), contact.mName.get(), Toast.LENGTH_LONG).show();
            }
        });
        // 通过getRoot()获取操作视图，并且在onCreateView中返回该视图，否则会导致binding不生效
        return mBinding.getRoot();
    }

    public void onViewClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_contact_name) {
            contact.mName.set("王五");
        } else if (id == R.id.tv_contact_phone) {
            contact.mPhone.set("132000000");
        } else if (id == R.id.tv_contact_type) {
            contact.mType.set(2);
        } else if (id == R.id.tv_first_name) {
            user.put("firstname", "li");
        } else if (id == R.id.tv_last_name) {
            user.put("lastname", "si");
        } else if (id == R.id.tv_age) {
            user.put("age", "48");
        } else if (id == R.id.tv_book_one) {
            books.set(0, "三国演义");
        } else if (id == R.id.tv_book_two) {
            books.set(1, "红楼梦");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBinding != null) {
            mBinding.unbind();
        }
    }
}