package cn.noteblog.library.widget.recycler;

import android.view.View;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 10:31
 */
public abstract class ConfigureAdapter implements Configure {

    @Override
    public void configureEmptyView(View emptyView) {

    }

    @Override
    public void configureErrorView(View errorView) {

    }

    @Override
    public void configureLoadingView(View loadingView) {

    }

    @Override
    public void configureLoadMoreView(View loadMoreView) {

    }

    @Override
    public void configureNoMoreView(View noMoreView) {

    }

    @Override
    public void configureLoadMoreFailedView(View loadMoreFailedView) {

    }
}
