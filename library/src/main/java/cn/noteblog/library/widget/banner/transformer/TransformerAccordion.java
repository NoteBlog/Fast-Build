package cn.noteblog.library.widget.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

class TransformerAccordion extends BaseTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setPivotX(view, view.getWidth());
        ViewHelper.setScaleX(view, 1.0f + position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setPivotX(view, 0);
        ViewHelper.setScaleX(view, 1.0f - position);
        ViewHelper.setAlpha(view, 1);
    }
}