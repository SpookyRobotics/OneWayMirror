package com.jsjrobotics.onewaymirror.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.jsjrobotics.onewaymirror.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WidgetProfile {
    public static final String EXTRA_WIDGET_ID = "widgetprovider.widget.id";

    public static final String WIDGET_PREFERENCE_FILE = "widget_persistence";
    private static final String WIDGET_MAP = "widgetprofile.widget_map";


    private final int mWidgetId;
    private final SharedPreferences mSharedPreferences;

    public WidgetProfile(SharedPreferences preferences, int widgetId){
        mWidgetId = widgetId;
        mSharedPreferences = preferences;
    }

    private String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(getKey(key), defaultValue);
    }

    private void saveString(String key, String value) {
        mSharedPreferences.edit().putString(getKey(key),value).apply();
    }

    public void saveInt(String key, int value) {
        mSharedPreferences.edit().putInt(getKey(key), value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(getKey(key), defaultValue);
    }

    private String getKey(String id) {
        return String.valueOf(mWidgetId) + id;
    }


    public static void updateWidgetIds(Context context, int... widgetIds){
        SharedPreferences preferences = Utils.getWidgetPreferences(context);
        Set<String> values = new HashSet<>();
        for(int id : widgetIds){
            values.add("/:" + id);
        }
        preferences.edit().putStringSet(WIDGET_MAP,values).apply();
    }

    public static void removeWidgetIds(Context context, int... ids){
        ArrayList<Integer> widgetIds = new ArrayList<>();
        for(int i : ids){
            widgetIds.add(i);
        }
        SharedPreferences preferences = Utils.getWidgetPreferences(context);
        Set<String> newValues = new HashSet<>();
        Set<String> oldValues = preferences.getStringSet(WIDGET_MAP, null);
        if(oldValues == null){
            return;
        }
        for(String s : oldValues){
            int colonIndex = s.indexOf(":");
            if(colonIndex == -1){
                continue;
            }
            int id = Integer.parseInt(s.substring(colonIndex + 1, s.length()));
            if(widgetIds.contains(id)){
                continue;
            }
            newValues.add(s);
        }
        preferences.edit().putStringSet(WIDGET_MAP,newValues).apply();
    }

    public static Set<String> getWidgetMap(Context context){
        SharedPreferences preferences = Utils.getWidgetPreferences(context);
        return preferences.getStringSet(WIDGET_MAP, null);
    }

    public int getWidgetId() {
        return mWidgetId;
    }

}
