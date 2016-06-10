package com.g_art.personalapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import com.g_art.personalapp.R;
import com.g_art.personalapp.activity.PersonalActivity;
import com.g_art.personalapp.util.ClickType;
import com.g_art.personalapp.util.DataStorage;
import com.g_art.personalapp.util.DataStorageImpl;

import java.util.Arrays;

/**
 * Created by fftem on 22-Apr-16.
 */
public class WidgetProvider extends AppWidgetProvider {
	private final String LOG_TAG = "Widget";

	private static final String VALUE = "value";
	private static final String EMPTY_VALUE = "0";
	private static final String EMPTY_OUTLAY = "Some outlay type";
	private static final String FIRST_OUTLAY_TYPE = "FIRST_OUTLAY_TYPE";
	private static final String SECOND_OUTLAY_TYPE = "SECOND_OUTLAY_TYPE";
	private static final String MORE_OUTLAY_TYPES = "MORE_OUTLAY_TYPES";
	private static final String ZERO_OUTLAY_VALUE = "0";
	private static final String ONE_OUTLAY_VALUE = "1";
	private static final String TWO_OUTLAY_VALUE = "2";
	private static final String THREE_OUTLAY_VALUE = "3";
	private static final String FOUR_OUTLAY_VALUE = "4";
	private static final String FIVE_OUTLAY_VALUE = "5";
	private static final String SIX_OUTLAY_VALUE = "6";
	private static final String SEVEN_OUTLAY_VALUE = "7";
	private static final String EIGHT_OUTLAY_VALUE = "8";
	private static final String NINE_OUTLAY_VALUE = "9";
	private static final String DELETE_OUTLAY_VALUE = "delete";
	private static final String PUSH = "push";

	private static DataStorage dataStorage;

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.i(LOG_TAG, "onEnabled");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
						 int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		//Get all ids
		ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

		dataStorage = DataStorageImpl.getInstance(context);

		for (int widgetId : allWidgetIds) {
			updateWidget(context, widgetId);
			Log.i(LOG_TAG, "onUpdate widgetId: " + widgetId);
		}
	}

	public static void updateWidget(final Context context, final int widgetId) {
		dataStorage = DataStorageImpl.getInstance(context);

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

		String value = dataStorage.getStoredValue();
		String firstOutlay = dataStorage.getFirstOutlayType();
		String secondOutlay = dataStorage.getSecondOutlayType();
		String selectedOutlay = dataStorage.getSelectedOutlayType();

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

		remoteViews.setTextViewText(R.id.btn_first_outlay_type, firstOutlay);
		remoteViews.setTextViewText(R.id.btn_sec_outlay_type, secondOutlay);
		if (selectedOutlay != null && !selectedOutlay.isEmpty()) {
			remoteViews.setTextViewText(R.id.txt_choose_outlay_type, selectedOutlay);
		}
		remoteViews.setTextViewText(R.id.txt_outlay_value, value);
		registerOnClickPendingIntent(context, remoteViews);

		appWidgetManager.updateAppWidget(widgetId, remoteViews);
	}

	private static void registerOnClickPendingIntent(Context context, RemoteViews remoteViews) {
		remoteViews.setOnClickPendingIntent(R.id.btn_add_outlay,
				getPendingSelfIntent(context, PUSH));
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
		remoteViews.setOnClickPendingIntent(R.id.btn_remove,
				getPendingSelfIntent(context, DELETE_OUTLAY_VALUE));
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		ClickType clickType = null;

		String action = intent.getAction();
		switch (action) {
			case FIRST_OUTLAY_TYPE:
				// your onClick action is here
				Log.i("Widget", "Clicked FIRST_OUTLAY_TYPE");
				clickType = ClickType.OUTLAY_FIRST_TYPE;
				break;
			case SECOND_OUTLAY_TYPE:
				Log.i("Widget", "Clicked SECOND_OUTLAY_TYPE");
				clickType = ClickType.OUTLAY_SECOND_TYPE;
				break;
			case MORE_OUTLAY_TYPES:
				Log.i("Widget", "Clicked MORE_OUTLAY_TYPES");
				clickType = ClickType.OUTLAY_MORE_TYPE;
				break;
			case ZERO_OUTLAY_VALUE:
				Log.i("Widget", "Clicked ZERO_OUTLAY_VALUE");
				clickType = ClickType.ZERO;
				break;
			case ONE_OUTLAY_VALUE:
				Log.i("Widget", "Clicked ONE_OUTLAY_VALUE");
				clickType = ClickType.ONE;
				break;
			case TWO_OUTLAY_VALUE:
				Log.i("Widget", "Clicked TWO_OUTLAY_VALUE");
				clickType = ClickType.TWO;
				break;
			case THREE_OUTLAY_VALUE:
				Log.i("Widget", "Clicked THREE_OUTLAY_VALUE");
				clickType = ClickType.THREE;
				break;
			case FOUR_OUTLAY_VALUE:
				Log.i("Widget", "Clicked FOUR_OUTLAY_VALUE");
				clickType = ClickType.FOUR;
				break;
			case FIVE_OUTLAY_VALUE:
				Log.i("Widget", "Clicked FIVE_OUTLAY_VALUE");
				clickType = ClickType.FIVE;
				break;
			case SIX_OUTLAY_VALUE:
				Log.i("Widget", "Clicked SIX_OUTLAY_VALUE");
				clickType = ClickType.SIX;
				break;
			case SEVEN_OUTLAY_VALUE:
				Log.i("Widget", "Clicked SEVEN_OUTLAY_VALUE");
				clickType = ClickType.SEVEN;
				break;
			case EIGHT_OUTLAY_VALUE:
				Log.i("Widget", "Clicked EIGHT_OUTLAY_VALUE");
				clickType = ClickType.EIGHT;
				break;
			case NINE_OUTLAY_VALUE:
				Log.i("Widget", "Clicked NINE_OUTLAY_VALUE");
				clickType = ClickType.NINE;
				break;
			case DELETE_OUTLAY_VALUE:
				Log.i("Widget", "Clicked DELETE_OUTLAY_VALUE");
				clickType = ClickType.DELETE;
				break;
			case PUSH:
				Log.i("Widget", "Clicked PUSH");
				clickType = ClickType.PUSH;
				break;
		}
		handleClick(context, clickType);
	}

	private void handleClick(Context context, ClickType clickType) {
		if (clickType != null) {
			ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

			int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

			dataStorage = DataStorageImpl.getInstance(context);
			String storedValue = dataStorage.getStoredValue();

			for (int widgetId : allWidgetIds) {

				RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				String newValue = storedValue;
				switch (clickType) {
					case DELETE:
						if (!newValue.equals(EMPTY_VALUE)) {
							if (storedValue.length() == 1) {
								newValue = EMPTY_VALUE;
							} else {
								newValue = storedValue.substring(0, storedValue.length() - 1);
							}
						}
						break;
					case PUSH:
						// TODO: 06-Jun-16 Push to server
						newValue = EMPTY_VALUE;
						remoteViews.setTextViewText(R.id.txt_choose_outlay_type, EMPTY_OUTLAY);
						break;
					case OUTLAY_FIRST_TYPE:
					case OUTLAY_SECOND_TYPE:
						String outlayType = "";
						if (clickType.equals(ClickType.OUTLAY_FIRST_TYPE)) {
							outlayType = dataStorage.getFirstOutlayType();
						} else {
							outlayType = dataStorage.getSecondOutlayType();
						}
						remoteViews.setTextViewText(R.id.txt_choose_outlay_type, outlayType);
						break;
					case OUTLAY_MORE_TYPE:
						//Activity will be opened
						break;
					default:
						if (storedValue.equals(EMPTY_VALUE)) {
							newValue = clickType.getClickValue();
						} else {
							newValue = storedValue + clickType.getClickValue();
						}
						break;
				}
				dataStorage.saveValue(newValue);
				remoteViews.setTextViewText(R.id.txt_outlay_value, newValue);
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
			}
		}
	}

	private static PendingIntent getPendingSelfIntent(Context context, String action) {
		if (MORE_OUTLAY_TYPES.equals(action)) {
			Intent intent = new Intent(context, PersonalActivity.class);
			intent.setAction(action);
			return PendingIntent.getActivity(context, 0, intent, 0);
		}
		Intent intent = new Intent(context, WidgetProvider.class);
		intent.setAction(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.i(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.i(LOG_TAG, "onDisabled");
	}
}
