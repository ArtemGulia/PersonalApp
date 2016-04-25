package com.g_art.personalapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.g_art.personalapp.R;

import java.util.Arrays;

/**
 * Created by fftem on 22-Apr-16.
 */
public class WidgetProvider extends AppWidgetProvider {
    final String LOG_TAG = "myLogs";
    private final String FIRST_OUTLAY_TYPE = "FIRST_OUTLAY_TYPE";
    private final String SECOND_OUTLAY_TYPE = "SECOND_OUTLAY_TYPE";
    private final String MORE_OUTLAY_TYPES = "MORE_OUTLAY_TYPES";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // Get all ids
        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            remoteViews.setOnClickPendingIntent(R.id.btn_first_outlay_type,
                    getPendingSelfIntent(context, FIRST_OUTLAY_TYPE));
            remoteViews.setOnClickPendingIntent(R.id.btn_sec_outlay_type,
                    getPendingSelfIntent(context, SECOND_OUTLAY_TYPE));
            remoteViews.setOnClickPendingIntent(R.id.btn_more_outlay_types,
                    getPendingSelfIntent(context, MORE_OUTLAY_TYPES));

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (FIRST_OUTLAY_TYPE.equals(intent.getAction())) {
            // your onClick action is here
            Toast.makeText(context, "FIRST_OUTLAY_TYPE", Toast.LENGTH_SHORT).show();
            Log.d("Widget", "Clicked FIRST_OUTLAY_TYPE");
        } else if (SECOND_OUTLAY_TYPE.equals(intent.getAction())) {
            Toast.makeText(context, "SECOND_OUTLAY_TYPE", Toast.LENGTH_SHORT).show();
            Log.d("Widget", "Clicked SECOND_OUTLAY_TYPE");
        } else if (MORE_OUTLAY_TYPES.equals(intent.getAction())) {
            Toast.makeText(context, "MORE_OUTLAY_TYPES", Toast.LENGTH_SHORT).show();
            Log.d("Widget", "Clicked MORE_OUTLAY_TYPES");
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
