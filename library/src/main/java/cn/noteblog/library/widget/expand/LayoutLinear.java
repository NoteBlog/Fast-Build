package cn.noteblog.library.widget.expand;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.noteblog.library.R;

public class LayoutLinear extends LinearLayout implements Expandable {

    public static final int ACCELERATE_DECELERATE_INTERPOLATOR = 0;
    public static final int ACCELERATE_INTERPOLATOR = 1;
    public static final int ANTICIPATE_INTERPOLATOR = 2;
    public static final int ANTICIPATE_OVERSHOOT_INTERPOLATOR = 3;
    public static final int BOUNCE_INTERPOLATOR = 4;
    public static final int DECELERATE_INTERPOLATOR = 5;
    public static final int FAST_OUT_LINEAR_IN_INTERPOLATOR = 6;
    public static final int FAST_OUT_SLOW_IN_INTERPOLATOR = 7;
    public static final int LINEAR_INTERPOLATOR = 8;
    public static final int LINEAR_OUT_SLOW_IN_INTERPOLATOR = 9;
    public static final int OVERSHOOT_INTERPOLATOR = 10;

    private int duration;
    // Default state of expanse
    private boolean defaultExpanded;
    /**
     * You cannot define defaultExpanded, defaultChildIndex and defaultPosition at the same time. defaultPosition has priority over defaultExpandedand defaultChildIndex if you set them at the same time.
     * Priority：{@link #defaultPosition} > {@link #defaultChildIndex} > {@link #defaultExpanded}
     */
    private int defaultChildIndex;
    private int defaultPosition;
    private TimeInterpolator interpolator = new LinearInterpolator();
    private boolean isExpanded;

    private boolean isCalculatedSize = false;
    // view size of children
    private List<Integer> childSizeList = new ArrayList<>();
    private int layoutSize = 0;
    private boolean isArranged = false;
    /**
     * The close position is width from left of layout if orientation is horizontal.
     * The close position is height from top of layout if orientation is vertical.
     */
    private int closePosition = 0;
    private boolean isAnimating = false;
    private ExpandableListener listener;
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;

    private SavedState savedState;

    public LayoutLinear(final Context context) {
        this(context, null);
    }

    public LayoutLinear(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutLinear(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LayoutLinear(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Expandable, defStyleAttr, 0);
        duration = a.getInteger(R.styleable.Expandable_exp_duration, DEFAULT_DURATION);
        defaultExpanded = a.getBoolean(R.styleable.Expandable_exp_expanded, DEFAULT_EXPANDED);
        defaultChildIndex = a.getInteger(R.styleable.Expandable_exp_defaultChildIndex, Integer.MAX_VALUE);
        defaultPosition = a.getDimensionPixelSize(R.styleable.Expandable_exp_defaultPosition, Integer.MIN_VALUE);
        final int interpolatorType = a.getInteger(R.styleable.Expandable_exp_interpolator, LINEAR_INTERPOLATOR);
        a.recycle();
        interpolator = createInterpolator(interpolatorType);
        isExpanded = defaultExpanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!isCalculatedSize) {
            // calculate a size of children
            childSizeList.clear();
            final int childCount = getChildCount();
            int sumSize = 0;
            View view;
            LayoutParams params;
            for (int i = 0; i < childCount; i++) {
                view = getChildAt(i);
                params = (LayoutParams) view.getLayoutParams();

                if (0 < i) {
                    sumSize = childSizeList.get(i - 1);
                }
                childSizeList.add((isVertical() ? view.getMeasuredHeight() + params.topMargin + params.bottomMargin : view.getMeasuredWidth() + params.leftMargin + params.rightMargin) + sumSize);
            }
            layoutSize = childSizeList.get(childCount - 1) + (isVertical() ? getPaddingTop() + getPaddingBottom() : getPaddingLeft() + getPaddingRight());
            isCalculatedSize = true;
        }

        if (isArranged) return;

        // adjust default position if a user set a value.
        if (!defaultExpanded) {
            setLayoutSize(closePosition);
        }
        final int childNumbers = childSizeList.size();
        if (childNumbers > defaultChildIndex && childNumbers > 0) {
            moveChild(defaultChildIndex, 0, null);
        }
        if (defaultPosition > 0 && layoutSize >= defaultPosition && layoutSize > 0) {
            move(defaultPosition, 0, null);
        }
        isArranged = true;

        if (savedState == null) return;
        setLayoutSize(savedState.getSize());
    }

    private boolean isVertical() {
        return getOrientation() == LinearLayout.VERTICAL;
    }

    private void setLayoutSize(int size) {
        if (isVertical()) {
            getLayoutParams().height = size;
        } else {
            getLayoutParams().width = size;
        }
    }

    /**
     * @param index child view index
     * @see #moveChild(int, long, TimeInterpolator)
     */
    private void moveChild(int index) {
        moveChild(index, duration, interpolator);
    }

    /**
     * Moves to bottom(VERTICAL) or right(HORIZONTAL) of child view Sets 0 to duration if you want to move immediately.
     *
     * @param index        index child view index
     * @param interpolator use the default interpolator if the argument is null.
     */
    private void moveChild(int index, long duration, @Nullable TimeInterpolator interpolator) {
        if (isAnimating) return;

        final int destination = getChildPosition(index) + (isVertical() ? getPaddingBottom() : getPaddingRight());
        if (duration <= 0) {
            isExpanded = destination > closePosition;
            setLayoutSize(destination);
            requestLayout();
            notifyListeners();
            return;
        }
        createExpandAnimator(getCurrentPosition(), destination, duration, interpolator == null ? this.interpolator : interpolator).start();
    }

    /**
     * Gets the width from left of layout if orientation is horizontal.
     * Gets the height from top of layout if orientation is vertical.
     *
     * @param index index of child view
     * @return position from top or left
     */
    private int getChildPosition(final int index) {
        if (0 > index || childSizeList.size() <= index) {
            throw new IllegalArgumentException("There aren't the view having this index.");
        }
        return childSizeList.get(index);
    }

    /**
     * Notify listeners
     */
    private void notifyListeners() {
        if (listener == null) return;

        listener.onAnimationStart();
        if (isExpanded) {
            listener.onPreOpen();
        } else {
            listener.onPreClose();
        }
        mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(mGlobalLayoutListener);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(mGlobalLayoutListener);
                }

                listener.onAnimationEnd();
                if (isExpanded) {
                    listener.onOpened();
                } else {
                    listener.onClosed();
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(mGlobalLayoutListener);
    }

    /**
     * Creates value animator.
     * Expand the layout if {@param to} is bigger than {@param from}.
     * Collapse the layout if {@param from} is bigger than {@param to}.
     */
    private ValueAnimator createExpandAnimator(final int from, final int to, final long duration, final TimeInterpolator interpolator) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(from, to);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animator) {
                if (isVertical()) {
                    getLayoutParams().height = (int) animator.getAnimatedValue();
                } else {
                    getLayoutParams().width = (int) animator.getAnimatedValue();
                }
                requestLayout();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimating = true;
                if (listener == null) return;

                listener.onAnimationStart();
                if (layoutSize == to) {
                    listener.onPreOpen();
                    return;
                }
                if (closePosition == to) {
                    listener.onPreClose();
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimating = false;
                isExpanded = to > closePosition;

                if (listener == null) return;

                listener.onAnimationEnd();
                if (to == layoutSize) {
                    listener.onOpened();
                    return;
                }
                if (to == closePosition) {
                    listener.onClosed();
                }
            }
        });
        return valueAnimator;
    }

    /**
     * Gets the current position.
     */
    private int getCurrentPosition() {
        return isVertical() ? getMeasuredHeight() : getMeasuredWidth();
    }

    /**
     * @param position
     * @see #move(int, long, TimeInterpolator)
     */
    private void move(int position) {
        move(position, duration, interpolator);
    }

    /**
     * Moves to position. Sets 0 to duration if you want to move immediately.
     *
     * @param interpolator use the default interpolator if the argument is null.
     */
    private void move(int position, long duration, @Nullable TimeInterpolator interpolator) {
        if (isAnimating || 0 > position || layoutSize < position) return;

        if (duration <= 0) {
            isExpanded = position > closePosition;
            setLayoutSize(position);
            requestLayout();
            notifyListeners();
            return;
        }
        createExpandAnimator(getCurrentPosition(), position, duration, interpolator == null ? this.interpolator : interpolator).start();
    }

    @Override
    public void toggle() {
        toggle(duration, interpolator);
    }

    @Override
    public void toggle(long duration, @Nullable TimeInterpolator interpolator) {
        if (closePosition < getCurrentPosition()) {
            collapse(duration, interpolator);
        } else {
            expand(duration, interpolator);
        }
    }

    @Override
    public void expand() {
        if (isAnimating) return;

        createExpandAnimator(getCurrentPosition(), layoutSize, duration, interpolator).start();
    }

    @Override
    public void expand(long duration, @Nullable TimeInterpolator interpolator) {
        if (isAnimating) return;

        if (duration <= 0) {
            move(layoutSize, duration, interpolator);
            return;
        }
        createExpandAnimator(getCurrentPosition(), layoutSize, duration, interpolator).start();
    }

    @Override
    public void collapse() {
        if (isAnimating) return;

        createExpandAnimator(getCurrentPosition(), closePosition, duration, interpolator).start();
    }

    @Override
    public void collapse(long duration, @Nullable TimeInterpolator interpolator) {
        if (isAnimating) return;

        if (duration <= 0) {
            move(closePosition, duration, interpolator);
            return;
        }
        createExpandAnimator(getCurrentPosition(), closePosition, duration, interpolator).start();
    }

    @Override
    public void initLayout(boolean isMaintain) {
        closePosition = 0;
        layoutSize = 0;
        isArranged = false;
        isCalculatedSize = false;
        savedState = null;

        requestLayout();
    }

    @Override
    public void setListener(@NonNull ExpandableListener listener) {
        this.listener = listener;
    }

    @Override
    public void setDuration(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + duration);
        }
        this.duration = duration;
    }

    @Override
    public void setExpanded(boolean expanded) {
        final int currentPosition = getCurrentPosition();
        if ((expanded && (currentPosition == layoutSize)) || (!expanded && currentPosition == closePosition))
            return;

        isExpanded = expanded;
        setLayoutSize(expanded ? layoutSize : closePosition);
        requestLayout();
    }

    @Override
    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public void setInterpolator(@NonNull TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable parcelable = super.onSaveInstanceState();
        final SavedState ss = new SavedState(parcelable);
        ss.setSize(getCurrentPosition());
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(final Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        final SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        savedState = ss;
    }

    /**
     * Gets the width from left of layout if orientation is horizontal.
     * Gets the height from top of layout if orientation is vertical.
     *
     * @see #closePosition
     */
    public int getClosePosition() {
        return closePosition;
    }

    /**
     * Sets the close position directly.
     *
     * @see #closePosition
     * @see #setClosePositionIndex(int)
     */
    public void setClosePosition(final int position) {
        this.closePosition = position;
    }

    /**
     * Sets close position using index of child view.
     *
     * @see #closePosition
     * @see #setClosePosition(int)
     */
    public void setClosePositionIndex(final int childIndex) {
        this.closePosition = getChildPosition(childIndex);
    }

    /**
     * Creates Interpolator.
     *
     * @param interpolatorType type
     */
    public static TimeInterpolator createInterpolator(@IntRange(from = 0, to = 10) final int interpolatorType) {
        switch (interpolatorType) {
            case ACCELERATE_DECELERATE_INTERPOLATOR:
                return new AccelerateDecelerateInterpolator();
            case ACCELERATE_INTERPOLATOR:
                return new AccelerateInterpolator();
            case ANTICIPATE_INTERPOLATOR:
                return new AnticipateInterpolator();
            case ANTICIPATE_OVERSHOOT_INTERPOLATOR:
                return new AnticipateOvershootInterpolator();
            case BOUNCE_INTERPOLATOR:
                return new BounceInterpolator();
            case DECELERATE_INTERPOLATOR:
                return new DecelerateInterpolator();
            case FAST_OUT_LINEAR_IN_INTERPOLATOR:
                return new FastOutLinearInInterpolator();
            case FAST_OUT_SLOW_IN_INTERPOLATOR:
                return new FastOutSlowInInterpolator();
            case LINEAR_INTERPOLATOR:
                return new LinearInterpolator();
            case LINEAR_OUT_SLOW_IN_INTERPOLATOR:
                return new LinearOutSlowInInterpolator();
            case OVERSHOOT_INTERPOLATOR:
                return new OvershootInterpolator();
            default:
                return new LinearInterpolator();
        }
    }
}
