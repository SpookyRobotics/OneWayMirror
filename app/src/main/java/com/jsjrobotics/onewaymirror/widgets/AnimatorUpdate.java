package com.jsjrobotics.onewaymirror.widgets;

import com.jsjrobotics.onewaymirror.data.WidgetUpdate;

class AnimatorUpdate implements WidgetUpdate {
    private final int mAnimationFrame;
    private final int mWidgetId;
    private final int mPreviousFrame;

    public AnimatorUpdate(int widgetId, int animationFrame, int previousFrame) {
        mWidgetId = widgetId;
        mAnimationFrame = animationFrame;
        mPreviousFrame = previousFrame;
    }


    @Override
    public int getWidgetId() {
        return mWidgetId;
    }

    public int getAnimationFrame(){
        return mAnimationFrame;
    }

    public int getPreviousFrame() {
        return mPreviousFrame;
    }
}
