package cn.noteblog.library.widget.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

class TransformerCube extends BaseTransformer {

    private float mMaxRotation = 90.0f;

    TransformerCube() {
    }

    public TransformerCube(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setPivotX(view, 0);
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    private void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 90.0f) {
            mMaxRotation = maxRotation;
        }
    }
}
