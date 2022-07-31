package com.example.databinding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.BR;
import com.example.databinding.callback.BindingAdapterItem;
import com.example.databinding.callback.OnRecyclerItemClickListener;

import java.util.List;

public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingHolder> {
    private Context context;
    private ObservableArrayList<BindingAdapterItem> mData;
    private OnRecyclerItemClickListener mOnItemClickListener;
    private ListChangedCallback itemsChangeCallback;

    public BindingAdapter(Context context) {
        this.context = context;
        mData = new ObservableArrayList<>();
        this.itemsChangeCallback = new BindingAdapter.ListChangedCallback();
    }

    /**
     * @return 返回的是adapter的view
     */
    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), viewType, parent, false);
        return new BindingHolder(binding);
    }

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindData(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getViewType();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mData.addOnListChangedCallback(itemsChangeCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mData.removeOnListChangedCallback(itemsChangeCallback);
    }


    public void setData(ObservableArrayList<BindingAdapterItem> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addAll(ObservableArrayList<BindingAdapterItem> data) {
        if (mData != null) {
            mData.addAll(data);
        }
    }

    /**
     * 添加item
     * 回调 ListChangedCallback.onItemRangeInserted方法
     *
     * @param position
     * @param item
     */
    public void addItem(int position, BindingAdapterItem item) {
        if (mData != null && position <= mData.size() - 1) {
            mData.add(position, item);
        }
    }

    /**
     * 修改item
     * 回调 ListChangedCallback.onItemRangeChanged方法
     *
     * @param position
     * @param item
     */
    public void setItem(int position, BindingAdapterItem item) {
        if (mData != null && position <= mData.size() - 1) {
            mData.set(position, item);
        }
    }

    /**
     * 删除item
     * 回调 ListChangedCallback.onItemRangeRemoved方法
     *
     * @param position
     */
    public void removeItem(int position) {
        if (mData != null && position <= mData.size() - 1) {
            mData.remove(position);
        }
    }

    /**
     * 清除数据
     * 回调 ListChangedCallback.onItemRangeRemoved方法
     */
    public void clear() {
        if (mData != null) {
            mData.clear();
        }
    }

    public List<BindingAdapterItem> getData() {
        return mData;
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        /**
         * getRoot()方法会返回整个holder的最顶层的view
         *
         * @param binding
         */
        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(BindingAdapterItem item) {
            binding.setVariable(BR.item, item);
        }

    }

    private class ListChangedCallback extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<BindingAdapterItem>> {
        @Override
        public void onChanged(ObservableArrayList<BindingAdapterItem> newItems) {
            setData(newItems);
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<BindingAdapterItem> newItems, int i, int i1) {
            setData(newItems);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<BindingAdapterItem> newItems, int i, int i1) {
            setData(newItems);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<BindingAdapterItem> newItems, int i, int i1, int i2) {
            setData(newItems);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<BindingAdapterItem> sender, int positionStart, int itemCount) {
            setData(sender);
        }
    }

}

