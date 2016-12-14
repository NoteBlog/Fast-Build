package cn.noteblog.library.widget.recycler;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 10:31
 * item的分区，如头部banner分区和底部loadmore分区
 */
public interface SectionItem {

    View createView(ViewGroup parent);

    void onBind();
}
