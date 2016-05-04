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
	private final String ZERO_OUTLAY_VALUE = "0";
	private final String ONE_OUTLAY_VALUE = "1";
	private final String TWO_OUTLAY_VALUE = "2";
	private final String THREE_OUTLAY_VALUE = "3";
	private final String FOUR_OUTLAY_VALUE = "4";
	private final String FIVE_OUTLAY_VALUE = "5";
	private final String SIX_OUTLAY_VALUE = "6";
	private final String SEVEN_OUTLAY_VALUE = "7";
	private final String EIGHT_OUTLAY_VALUE = "8";
	private final String NINE_OUTLAY_VALUE = "9";

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.d(LOG_TAG, "onEnabled");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
						 int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
//         Get all ids
		ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		for (int widgetId : allWidgetIds) {

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

			registerOnClickPendingIntent(context, remoteViews);

			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}

		Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));
	}

	private void registerOnClickPendingIntent(Context context, RemoteViews remoteViews) {
		remoteViews.setOnClickPendingIntent(R.id.btn_first_outlay_type,
				getPendingSelfIntent(context, FIRST_OUTLAY_TYPE));
		remoteViews.setOnClickPendingIntent(R.id.btn_sec_outlay_type,
				getPendingSelfIntent(context, SECOND_OUTLAY_TYPE));
		remoteViews.setOnClickPendingIntent(R.id.btn_more_outlay_types,
				getPendingSelfIntent(context, MORE_OUTLAY_TYPES));
		remoteViews.setOnClickPendingIntent(R.id.btn_zero_plus,
				getPendingSelfIntent(context, ZERO_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_one_plus,
				getPendingSelfIntent(context, ONE_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_two_plus,
				getPendingSelfIntent(context, TWO_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_three_plus,
				getPendingSelfIntent(context, THREE_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_four_plus,
				getPendingSelfIntent(context, FOUR_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_five_plus,
				getPendingSelfIntent(context, FIVE_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_six_plus,
				getPendingSelfIntent(context, SIX_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_seven_plus,
				getPendingSelfIntent(context, SEVEN_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_eight_plus,
				getPendingSelfIntent(context, EIGHT_OUTLAY_VALUE));
		remoteViews.setOnClickPendingIntent(R.id.btn_nine_plus,
				getPendingSelfIntent(context, NINE_OUTLAY_VALUE));
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		if (FIRST_OUTLAY_TYPE.equals(intent.getAction())) {
			// your onClick action is here
			Toast.makeText(context, FIRST_OUTLAY_TYPE, Toast.LENGTH_SHORT).show();
			Log.d("Widget", "Clicked FIRST_OUTLAY_TYPE");
		} else if (SECOND_OUTLAY_TYPE.equals(intent.getAction())) {
			Toast.makeText(context, FIRST_OUTLAY_TYPE, Toast.LENGTH_SHORT).show();
			Log.d("Widget", "Clicked SECOND_OUTLAY_TYPE");
		} else if (MORE_OUTLAY_TYPES.equals(intent.getAction())) {
			Toast.makeText(context, FIRST_OUTLAY_TYPE, Toast.LENGTH_SHORT).show();
			Log.d("Widget", "Clicked MORE_OUTLAY_TYPES");
		}

		super.onReceive(context, intent);
	}

	protected PendingIntent getPendingSelfIntent(Context context, String action) {
		Intent intent = new Intent(context, getClass());
		intent.setAction(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
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
}
