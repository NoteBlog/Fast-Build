package cn.noteblog.library.widget.expand;

public interface ExpandableListener {
    /**
     * Notifies the start of the animation.
     */
    void onAnimationStart();

    /**
     * Notifies the end of the animation.
     */
    void onAnimationEnd();

    /**
     * Notifies the layout is going to open.
     */
    void onPreOpen();

    /**
     * Notifies the layout is going to equal close size.
     */
    void onPreClose();

    /**
     * Notifies the layout opened.
     */
    void onOpened();

    /**
     * Notifies the layout size equal closed size.
     */
    void onClosed();
}
