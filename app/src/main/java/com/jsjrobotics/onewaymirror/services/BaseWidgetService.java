package com.jsjrobotics.onewaymirror.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jsjrobotics.onewaymirror.Utils;
import com.jsjrobotics.onewaymirror.data.WidgetProfile;
import com.jsjrobotics.onewaymirror.data.WidgetUpdate;

public abstract class BaseWidgetService<T extends WidgetUpdate> extends Service implements IWidgetService<T>{

    private int mWidgetId;


    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mWidgetId = intent.getIntExtra(WidgetProfile.EXTRA_WIDGET_ID, -1);
        if(mWidgetId == -1){
            Log.e(getClass().toString(), "Widget Id not found, stopping service");
            stopSelf();
            return START_NOT_STICKY;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final Context context = getApplicationContext();
                final T update = buildWidgetUpdate(context,mWidgetId);
                if(update != null){
                    saveWidgetState(context, update);
                    Intent i = buildWidgetUpdateIntent(context,update);
                    setActionAndWidgetId(i,update.getWidgetId());
                    sendBroadcast(i);
                }
                stopSelf();
            }
        });
        t.start();
        return START_STICKY;
    }

    @Override
    public void setActionAndWidgetId(Intent intent,int id){
        intent.setAction(getIntentUpdateAction() + ":" + id);
        intent.putExtra(WidgetProfile.EXTRA_WIDGET_ID, id);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
