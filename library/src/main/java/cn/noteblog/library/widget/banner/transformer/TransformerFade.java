package cn.noteblog.library.widget.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

class TransformerFade extends BaseTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);
        ViewHelper.setAlpha(view, 1 + position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);
        ViewHelper.setAlpha(view, 1 - position);
    }
}