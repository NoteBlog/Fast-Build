package cn.noteblog.library.widget.banner;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

import java.util.List;

/**
 * Author: FynnHan(18330080926@163.com)
 * Date: 2016/11/15
 * Time: 11:43
 * 轮播图工具类
 */
class BannerUtil {

    static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    static ImageView getItemImageView(Context context, @DrawableRes int placeholderResId) {
        return getItemImageView(context, placeholderResId, ImageView.ScaleType.CENTER_CROP);
    }

    private static ImageView getItemImageView(Context context, @DrawableRes int placeholderResId, ImageView.ScaleType scaleType) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(placeholderResId);
        imageView.setClickable(true);
        imageView.setScaleType(scaleType);
        return imageView;
    }

    static void resetPageTransformer(List<? extends View> views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
            ViewHelper.setAlpha(view, 1);
            ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
            ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
            ViewHelper.setTranslationX(view, 0);
            ViewHelper.setTranslationY(view, 0);
            ViewHelper.setScaleX(view, 1);
            ViewHelper.setScaleY(view, 1);
            ViewHelper.setRotationX(view, 0);
            ViewHelper.setRotationY(view, 0);
            ViewHelper.setRotation(view, 0);
        }
    }
}
