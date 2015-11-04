package com.jsjrobotics.onewaymirror;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.jsjrobotics.onewaymirror.data.WidgetProfile;
import com.jsjrobotics.onewaymirror.data.WidgetUpdate;
import com.jsjrobotics.onewaymirror.services.BaseWidgetService;

import java.util.ArrayList;

public abstract class BaseWidgetProvider extends AppWidgetProvider {

    protected BaseWidgetService mWidgetService;
    public static final String EXTRA_WIDGET_ID = "widgetprovider.widget.id";

    @Override
    public void onUpdate (Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for(int id : appWidgetIds) {
            Log.i(getClass().toString(), "Building New Widget " + id);
            RemoteViews views = mWidgetService.buildInitialRemoteViews(context, id);
            if (views != null) {
                Intent onClickIntent = new Intent(context, mWidgetService.getClass());
                mWidgetService.setActionAndWidgetId(onClickIntent,id);
                PendingIntent pendingIntent = PendingIntent.getService(context, 0, onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                views.setOnClickPendingIntent(R.id.widget_holder, pendingIntent);
                appWidgetManager.updateAppWidget(id, views);
            }
        }
    }



    @Override
    public void onAppWidgetOptionsChanged (Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions){
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onReceive(Context context,Intent intent) {
        mWidgetService = getBaseWidgetService();
        super.onReceive(context, intent);
        if (mWidgetService.getIntentUpdateAction() != null && intent.getAction().startsWith(mWidgetService.getIntentUpdateAction())) {
            int widgetId = intent.getIntExtra(EXTRA_WIDGET_ID, -1);
            if(widgetId != -1) {
                RemoteViews views = mWidgetService.buildPartialUpdate(context, widgetId);
                AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(widgetId, views);
            }
        }
    }


    public abstract BaseWidgetService getBaseWidgetService();


    @Override
    public void onDeleted(Context context, int[] appWidgetIds){
        super.onDeleted(context, appWidgetIds);
        WidgetProfile.removeWidgetIds(context,appWidgetIds);
    }
}
