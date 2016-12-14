package cn.noteblog.library.widget.banner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/15
 * Time: 11:33
 * ViewPager切换效果的基类
 */
public abstract class BaseTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {
        if (position < -1.0f) {
            // [-Infinity,-1)
            // This page is way off-screen to the left.
            handleInvisiblePage(view, position);
        } else if (position <= 0.0f) {
            // [-1,0]
            // Use the default slide transition when moving to the left page
            handleLeftPage(view, position);
        } else if (position <= 1.0f) {
            // (0,1]
            handleRightPage(view, position);
        } else {
            // (1,+Infinity]
            // This page is way off-screen to the right.
            handleInvisiblePage(view, position);
        }
    }

    public abstract void handleInvisiblePage(View view, float position);

    public abstract void handleLeftPage(View view, float position);

    public abstract void handleRightPage(View view, float position);

    public static BaseTransformer getPageTransformer(TransitionType effect) {
        switch (effect) {
            case Default:
                return new TransformerDefault();
            case Alpha:
                return new TransformerAlpha();
            case Rotate:
                return new TransformerRotate();
            case Cube:
                return new TransformerCube();
            case Flip:
                return new TransformerFlip();
            case Accordion:
                return new TransformerAccordion();
            case ZoomFade:
                return new TransformerZoomFade();
            case Fade:
                return new TransformerFade();
            case ZoomCenter:
                return new TransformerZoomCenter();
            case ZoomStack:
                return new TransformerZoomStack();
            case Stack:
                return new TransformerStack();
            case Depth:
                return new TransformerDepth();
            case Zoom:
                return new TransformerZoom();
            default:
                return new TransformerDefault();
        }
    }
}