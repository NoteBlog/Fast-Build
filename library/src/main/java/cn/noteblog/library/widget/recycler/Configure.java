package cn.noteblog.library.widget.recycler;

import android.view.View;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 11:20
 * 配置要显示的视图布局
 */
public interface Configure {

    void configureEmptyView(View emptyView);

    void configureErrorView(View errorView);

    void configureLoadingView(View loadingView);

    void configureLoadMoreView(View loadMoreView);

    void configureNoMoreView(View noMoreView);

    void configureLoadMoreFailedView(View loadMoreFailedView);
}
