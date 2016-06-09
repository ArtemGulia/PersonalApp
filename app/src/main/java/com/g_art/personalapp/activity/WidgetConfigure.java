package com.g_art.personalapp.activity;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.g_art.personalapp.util.DataStorage;
import com.g_art.personalapp.util.DataStorageImpl;
import com.g_art.personalapp.widget.WidgetProvider;

/**
 * PersonalApp
 * Created by fftem on 08-Jun-16.
 */

public class WidgetConfigure extends AppCompatActivity {
	private int mAppWidgetId;
	private DataStorage dataStorage;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set the result to CANCELED. This will cause the widget host to cancel
		// out of the widget placement if the user presses the back button.
		setResult(RESULT_CANCELED);

		dataStorage = DataStorageImpl.getInstance(this);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		initData();

		// Make sure we pass back the original appWidgetId
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);

		WidgetProvider.updateWidget(getApplicationContext(), mAppWidgetId);
		// Destroy activity
		finish();
	}

	private void initData() {
		// TODO: 07-Jun-16 Go to server for last outlay types
		dataStorage.setFirstOutlayType("test1");
		dataStorage.setSecondOutlayType("test2");
	}
}
