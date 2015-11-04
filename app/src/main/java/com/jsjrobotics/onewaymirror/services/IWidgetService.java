package com.jsjrobotics.onewaymirror.services;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jsjrobotics.onewaymirror.data.WidgetUpdate;

public interface IWidgetService<T extends WidgetUpdate> {
    RemoteViews buildInitialRemoteViews(Context context, int widgetId);
    RemoteViews buildPartialUpdate(Context context, int widgetId);
    void saveWidgetState(Context context,final T update);
    Intent buildWidgetUpdateIntent(Context context,final T update);
    T buildWidgetUpdate(Context context,int widgetId);

    String getIntentUpdateAction();

    void setActionAndWidgetId(Intent intent, int id);
}
