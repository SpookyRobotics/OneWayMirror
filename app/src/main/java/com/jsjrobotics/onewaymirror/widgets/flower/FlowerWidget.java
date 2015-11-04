package com.jsjrobotics.onewaymirror.widgets.flower;

import android.widget.RemoteViews;

import com.jsjrobotics.onewaymirror.BaseWidgetProvider;
import com.jsjrobotics.onewaymirror.R;
import com.jsjrobotics.onewaymirror.services.BaseWidgetService;
import com.jsjrobotics.onewaymirror.widgets.AnimatorWidget;

public class FlowerWidget extends AnimatorWidget {
    private static final int[] grassStrips = {R.id.grass_strip0,R.id.grass_strip1,R.id.grass_strip2,R.id.grass_strip3,R.id.grass_strip4,R.id.grass_strip5};
    private static final int[] cutGrassStrips = {R.id.cut_grass_strip0,R.id.cut_grass_strip1,R.id.cut_grass_strip2,R.id.cut_grass_strip3,R.id.cut_grass_strip4,R.id.cut_grass_strip5};
    private static final int[][] animatorList = {grassStrips,cutGrassStrips};
    @Override
    public int[][] getAnimatorList() {
        return animatorList;
    }

    @Override
    public int getLayout() {
        return R.layout.widget_layout_flower;
    }

    @Override
    public Class<? extends BaseWidgetProvider> getProviderService() {
        return FlowerWidgetService.class;
    }

    @Override
    public int getTotalFrames() {
        return grassStrips.length;
    }

    @Override
    protected boolean eraseAllSpritesOnCreate() {
        return false;
    }

    @Override
    protected void frameSpecificUpdate(int currentFrame, RemoteViews views) {
        // Do nothing 
    }

    public static class FlowerWidgetService extends BaseWidgetProvider {
        @Override
        public BaseWidgetService getBaseWidgetService() {
            return new FlowerWidget();
        }
    }
}
