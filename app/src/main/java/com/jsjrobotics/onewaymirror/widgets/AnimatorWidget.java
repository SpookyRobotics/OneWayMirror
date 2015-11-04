package com.jsjrobotics.onewaymirror.widgets;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.jsjrobotics.onewaymirror.BaseWidgetProvider;
import com.jsjrobotics.onewaymirror.Utils;
import com.jsjrobotics.onewaymirror.data.WidgetProfile;
import com.jsjrobotics.onewaymirror.services.BaseWidgetService;

public abstract class AnimatorWidget extends BaseWidgetService<AnimatorUpdate>{
    private static final String ANIMATOR_FRAME = "animatorwidget.update_animator";
    private static final String ANIMATE_FRAME = "animatorwidget.animate_frame";
    private static final String PREVIOUS_FRAME = "animatorwidget.previous_frame";
    protected static final int SKIP_FRAME = -1;

    protected abstract int[][] getAnimatorList();
    protected abstract int getLayout();
    protected abstract Class<? extends BaseWidgetProvider> getProviderService();
    protected abstract int getTotalFrames();
    protected abstract boolean eraseAllSpritesOnCreate();
    protected abstract void frameSpecificUpdate(int currentFrame, RemoteViews views);


    public AnimatorWidget(){
        if(getTotalFrames() < 0){
            throw new IllegalArgumentException("getTotalFrames must be >= 0");
        }
        if(getIntentUpdateAction() == null){
            throw new IllegalArgumentException("getIntentUpdateAction must not be null");
        }
    }

    @Override
    public RemoteViews buildInitialRemoteViews(Context context, int widgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), getLayout());
        if(eraseAllSpritesOnCreate()) {
            for (int[] spriteList : getAnimatorList()) {
                for (int index = 0; index < spriteList.length; index++) {
                    if(spriteList[index] == SKIP_FRAME){
                        continue;
                    }
                    views.setViewVisibility(spriteList[index], View.INVISIBLE);
                }
            }
        }
        return views;
    }


    @Override
    public RemoteViews buildPartialUpdate(Context context, int widgetId) {
        WidgetProfile profile = Utils.buildWidgetProfile(context, widgetId);
        int currentFrame = profile.getInt(ANIMATOR_FRAME,-1);
        int previousFrame = profile.getInt(PREVIOUS_FRAME,-1);
        if(currentFrame == -1){
            return buildInitialRemoteViews(context,widgetId);
        }
        RemoteViews views = new RemoteViews(context.getPackageName(), getLayout());
        if(previousFrame >= 0) {
            disableSprites(views, previousFrame);
        }
        enableSprites(views, currentFrame);
        frameSpecificUpdate(currentFrame,views);
        return views;
    }


    private void disableSprites(RemoteViews views, int previousFrame) {
        for(int[] sprites : getAnimatorList()){
            if (previousFrame >= sprites.length || sprites[previousFrame] == SKIP_FRAME) {
                continue;
            }
            views.setViewVisibility(sprites[previousFrame], View.INVISIBLE);
        }
    }

    private void enableSprites(RemoteViews views, int currentFrame) {
        for(int[] sprites : getAnimatorList()){
            if (currentFrame >= sprites.length || sprites[currentFrame] == SKIP_FRAME) {
                continue;
            }
            views.setViewVisibility(sprites[currentFrame], View.VISIBLE);
        }
    }

    @Override
    public void saveWidgetState(Context context, AnimatorUpdate update) {
        WidgetProfile profile = Utils.buildWidgetProfile(context, update.getWidgetId());
        profile.saveInt(ANIMATOR_FRAME, update.getAnimationFrame());
        profile.saveInt(PREVIOUS_FRAME, update.getPreviousFrame());
    }

    @Override
    public Intent buildWidgetUpdateIntent(Context context, AnimatorUpdate update) {
        Intent intent = new Intent(context, getProviderService());
        intent.putExtra(ANIMATE_FRAME,update.getAnimationFrame());
        intent.putExtra(PREVIOUS_FRAME, update.getPreviousFrame());
        return intent;
    }




    @Override
    // Get and increment frame count
    public AnimatorUpdate buildWidgetUpdate(Context context, int widgetId) {
        WidgetProfile profile = Utils.buildWidgetProfile(context, widgetId);
        int currentFrame = profile.getInt(ANIMATOR_FRAME, -1);
        currentFrame += 1;
        int frames = getTotalFrames();
        AnimatorUpdate update;
        if (currentFrame == frames) {
            update = new AnimatorUpdate(widgetId,0,frames-1);
        }
        else{
            update = new AnimatorUpdate(widgetId,currentFrame, currentFrame-1);
        }
        return update;
    }

    @Override
    public String getIntentUpdateAction() {
        return getClass().toString();
    }



}
