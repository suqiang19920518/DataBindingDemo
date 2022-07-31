package com.example.databinding.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databinding.callback.OnRecyclerItemClickListener;

/**
 * 只支持单类型item
 *
 * @param <M>
 * @param <DB>
 */
public abstract class BaseBindingAdapter<M extends BaseObservable, DB extends ViewDataBinding> extends RecyclerView.Adapter {
    protected Context context;
    protected ObservableArrayList<M> mData;
    protected OnRecyclerItemClickListener mOnItemClickListener;
    protected ListChangedCallback itemsChangeCallback;

    public BaseBindingAdapter(Context context) {
        this.context = context;
        this.mData = new ObservableArrayList<>();
        this.itemsChangeCallback = new ListChangedCallback();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DB binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(viewType), parent, false);
        return new BaseBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem(binding, mData.get(position));
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
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mData.addOnListChangedCallback(itemsChangeCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mData.removeOnListChangedCallback(itemsChangeCallback);
    }

    public void setData(ObservableArrayList<M> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addAll(ObservableArrayList<M> data) {
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
    public void addItem(int position, M item) {
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
    public void setItem(int position, M item) {
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

    public ObservableArrayList<M> getData() {
        return mData;
    }

    public M getItem(int position) {
        if (mData == null || position > mData.size() - 1) {
            return null;
        } else {
            return mData.get(position);
        }
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @LayoutRes
    protected abstract int getLayoutResId(int viewType);

    protected abstract void onBindItem(DB binding, M data);

    public static class BaseBindingViewHolder extends RecyclerView.ViewHolder {

        public BaseBindingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    protected class ListChangedCallback extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<M>> {
        @Override
        public void onChanged(ObservableArrayList<M> newItems) {
            setData(newItems);
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<M> newItems, int i, int i1) {
            setData(newItems);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<M> newItems, int i, int i1) {
            setData(newItems);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<M> newItems, int i, int i1, int i2) {
            setData(newItems);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<M> sender, int positionStart, int itemCount) {
            setData(sender);
        }
    }

}
