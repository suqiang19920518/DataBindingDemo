package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.adapter.BindingAdapter;
import com.example.databinding.adapter.UserAdapter;
import com.example.databinding.callback.BindingAdapterItem;
import com.example.databinding.callback.OnRecyclerItemClickListener;
import com.example.databinding.databinding.ActivityMultiTypeBinding;
import com.example.databinding.field.Book;
import com.example.databinding.field.TextBean;
import com.example.databinding.field.User;

import java.util.List;

public class MultiTypeRecyclerViewActivity extends AppCompatActivity {

    private ActivityMultiTypeBinding dataBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_multi_type);
        initView();
    }

    private void initView() {
        BindingAdapter bindingAdapter = new BindingAdapter(this);
        ObservableArrayList<BindingAdapterItem> items = new ObservableArrayList<>();
        items.add(new TextBean("哈哈哈哈"));
        for (int i = 0; i < 30; i++) {
            BindingAdapterItem item = null;
            if (i == 2) {
                item = new TextBean("我又来啦");
            } else if (i == 5) {
                item = new Book("红楼梦" + i, "曹雪芹" + i, true);
            } else if (i == 7) {
                item = new TextBean("我还来");
            } else if (i == 8) {
                item = new TextBean("就是不让你看美女");
            } else if (i == 13) {
                item = new TextBean("哈哈你当不住我看见啦");
            } else {
                item = new Book("红楼梦" + i, "曹雪芹" + i, false);
            }
            items.add(item);
        }

        bindingAdapter.setData(items);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setAdapter(bindingAdapter);
        bindingAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<BindingAdapterItem> data = bindingAdapter.getData();
                BindingAdapterItem item = data.get(position);
                int viewType = item.getViewType();
                if (viewType == R.layout.text_item) {
                    Toast.makeText(MultiTypeRecyclerViewActivity.this, position + "", Toast.LENGTH_LONG).show();
                } else if (viewType == R.layout.book_item) {
                    if (position == 15) {
                        bindingAdapter.removeItem(position);
                        Toast.makeText(MultiTypeRecyclerViewActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                    } else if (position == 16) {
                        Book book = new Book("西游记", "吴承恩", false);
                        bindingAdapter.setItem(position, book);
                        Toast.makeText(MultiTypeRecyclerViewActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                    } else if (position == 17) {
                        Book book = new Book("水浒传", "施耐庵", false);
                        bindingAdapter.addItem(position, book);
                        Toast.makeText(MultiTypeRecyclerViewActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                    } else if (position == 18) {
                        bindingAdapter.clear();
                        Toast.makeText(MultiTypeRecyclerViewActivity.this, "清除成功", Toast.LENGTH_LONG).show();
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            BindingAdapterItem adapterItem = data.get(i);
                            if (adapterItem instanceof Book) {
                                ((Book) adapterItem).setChecked(i == position);
                            }
                        }
                    }
                }


            }
        });
    }
}