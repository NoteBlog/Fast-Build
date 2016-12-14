package cn.noteblog.library.widget.banner;

import android.content.Context;
import android.widget.Scroller;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/15
 * Time: 11:43
 * 轮播图平缓滚动器
 */
class BannerScroller extends Scroller {

    private int mDuration = 1000;

    BannerScroller(Context context, int duration) {
        super(context);
        mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
