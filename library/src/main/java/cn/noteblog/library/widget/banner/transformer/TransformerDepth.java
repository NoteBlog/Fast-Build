package cn.noteblog.library.widget.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

class TransformerDepth extends BaseTransformer {

    private float mMinScale = 0.8f;

    TransformerDepth() {
    }

    public TransformerDepth(float minScale) {
        setMinScale(minScale);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewHelper.setAlpha(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setAlpha(view, 1);
        ViewHelper.setTranslationX(view, 0);
        ViewHelper.setScaleX(view, 1);
        ViewHelper.setScaleY(view, 1);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setAlpha(view, 1 - position);
        ViewHelper.setTranslationX(view, -view.getWidth() * position);
        float scale = mMinScale + (1 - mMinScale) * (1 - position);
        ViewHelper.setScaleX(view, scale);
        ViewHelper.setScaleY(view, scale);
    }

    private void setMinScale(float minScale) {
        if (minScale >= 0.6f && minScale <= 1.0f) {
            mMinScale = minScale;
        }
    }
}