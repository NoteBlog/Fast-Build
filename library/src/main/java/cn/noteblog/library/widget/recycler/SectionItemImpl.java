package cn.noteblog.library.widget.recycler;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/14
 * Time: 10:31
 */
public class SectionItemImpl implements SectionItem {

    private View mView;

    public SectionItemImpl() {
    }

    public SectionItemImpl(View view) {
        mView = view;
    }

    @Override
    public View createView(ViewGroup parent) {
        return mView;
    }

    @Override
    public void onBind() {

    }
}
