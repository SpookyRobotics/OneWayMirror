package com.jsjrobotics.onewaymirror.widgets.shooter;

import android.view.View;
import android.widget.RemoteViews;

import com.jsjrobotics.onewaymirror.BaseWidgetProvider;
import com.jsjrobotics.onewaymirror.R;
import com.jsjrobotics.onewaymirror.services.BaseWidgetService;
import com.jsjrobotics.onewaymirror.widgets.AnimatorWidget;


public class ShooterWidget extends AnimatorWidget {
    private static final int[] BULLET_COLUMN_1 = {
            R.id.bullet18, R.id.bullet17, R.id.bullet16, R.id.bullet15,R.id.bullet14, R.id.bullet13, R.id.bullet12, R.id.bullet11,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet18, R.id.bullet17, R.id.bullet16, R.id.bullet15,R.id.bullet14, R.id.bullet13, R.id.bullet12, R.id.bullet11,R.id.bullet10,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME};

    private static final int[] BULLET_COLUMN_2 = {
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet28, R.id.bullet27, R.id.bullet26, R.id.bullet25,R.id.bullet24, R.id.bullet23, R.id.bullet22, R.id.bullet21,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet28, R.id.bullet27, R.id.bullet26, R.id.bullet25,R.id.bullet24, R.id.bullet23, R.id.bullet22, R.id.bullet21,R.id.bullet20,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME};

    private static final int[] BULLET_COLUMN_3 = {
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet38, R.id.bullet37, R.id.bullet36, R.id.bullet35,R.id.bullet34, R.id.bullet33, R.id.bullet32, R.id.bullet31,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet38, R.id.bullet37, R.id.bullet36, R.id.bullet35,R.id.bullet34, R.id.bullet33, R.id.bullet32, R.id.bullet31,R.id.bullet30,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME};

    private static final int[] BULLET_COLUMN_4 = {
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet48, R.id.bullet47, R.id.bullet46, R.id.bullet45,R.id.bullet44, R.id.bullet43, R.id.bullet42, R.id.bullet41,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,SKIP_FRAME,
            R.id.bullet48, R.id.bullet47, R.id.bullet46, R.id.bullet45,R.id.bullet44, R.id.bullet43, R.id.bullet42, R.id.bullet41,R.id.bullet40};


    private static final int[] WIDGET_DISPLAYED_PLAYER_IDS = {
            R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,
            R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,
            R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,
            R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,
            R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,R.id.player1,
            R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,R.id.player2,
            R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,R.id.player3,
            R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4,R.id.player4};

    private static final int[][] animateList = {WIDGET_DISPLAYED_PLAYER_IDS,BULLET_COLUMN_1,BULLET_COLUMN_2,BULLET_COLUMN_3,BULLET_COLUMN_4};

    private static final int[] BRICKS = {
            R.id.brick1,R.id.brick2,R.id.brick3,R.id.brick4,
            R.id.brick5,R.id.brick6,R.id.brick7,R.id.brick8
    };

    @Override
    public int[][] getAnimatorList() {
        return animateList;
    }

    @Override
    public int getLayout() {
        return R.layout.widget_layout_shooter;
    }

    @Override
    public Class<? extends BaseWidgetProvider> getProviderService() {
        return ShooterProvider.class;
    }

    @Override
    public int getTotalFrames() {
        return BULLET_COLUMN_1.length;
    }

    @Override
    protected boolean eraseAllSpritesOnCreate() {
        return true;
    }

    @Override
    protected void frameSpecificUpdate(int currentFrame, RemoteViews views) {
        if(currentFrame == 0){
            for(int brick : BRICKS){
                views.setViewVisibility(brick, View.VISIBLE);
            }
        }
        else if(currentFrame < 32){
            int visibleBricks = currentFrame/8;
            for(int index = 0; index < visibleBricks; index++){
                views.setViewVisibility(BRICKS[index],View.INVISIBLE);
            }
        }
        else {
            int visibleBricks = 4 +(currentFrame-32)/9;
            for(int index=0; index < visibleBricks; index++){
                views.setViewVisibility(BRICKS[index],View.INVISIBLE);
            }
        }
    }

    public static class ShooterProvider extends BaseWidgetProvider {
        @Override
        public BaseWidgetService getBaseWidgetService() {
            return new ShooterWidget();
        }
    }
}
