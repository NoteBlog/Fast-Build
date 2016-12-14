package cn.noteblog.library.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 10:31
 * 下拉刷新上拉加载更多的viewholder
 */
public abstract class BaseViewHolder<T extends ItemType> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public abstract void setData(T data);
}
