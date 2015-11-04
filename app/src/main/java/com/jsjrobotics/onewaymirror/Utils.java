package com.jsjrobotics.onewaymirror;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;

import com.jsjrobotics.onewaymirror.data.WidgetProfile;

import java.util.Set;

public class Utils {
    public static SharedPreferences getWidgetPreferences(Fragment fragment){
        return fragment.getActivity().getSharedPreferences(WidgetProfile.WIDGET_PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getWidgetPreferences(Context context){
        return context.getSharedPreferences(WidgetProfile.WIDGET_PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public static WidgetProfile buildWidgetProfile(Context context, int id) {
        return new WidgetProfile(getWidgetPreferences(context),id);
    }


    public static WidgetProfile findWidgetProfile(Context context,String sender) {
        if(sender == null){
            return null;
        }
        Set<String> destinationMap = WidgetProfile.getWidgetMap(context);
        for(String s : destinationMap){
            if(s != null && s.contains(sender)){
                int start = s.indexOf(":")+1;
                String id = s.substring(start);
                int widgetId = Integer.parseInt(id);
                return new WidgetProfile(getWidgetPreferences(context),widgetId);
            }
        }
        return null;
    }

    public static WidgetProfile findWidgetProfile(Context context,int id) {
        String idString = ":"+String.valueOf(id);
        Set<String> destinationMap = WidgetProfile.getWidgetMap(context);
        if(destinationMap == null){
            return null;
        }
        for(String s : destinationMap){
            if(s != null && s.contains(idString)){
                return new WidgetProfile(getWidgetPreferences(context),id);
            }
        }
        return null;
    }
}
