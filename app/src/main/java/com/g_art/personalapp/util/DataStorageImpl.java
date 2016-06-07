package com.g_art.personalapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * PersonalApp
 * Created by fftem on 06-Jun-16.
 */
public class DataStorageImpl implements DataStorage{
	private static DataStorageImpl ourInstance;

	private static final String SHARED_STORED_DATA = "shared_prefs";
	private static final String STORED_VALUE = "stored_value";
	private static final String EMPTY_VALUE = "0";
	private static final String EMPTY_OUTLAY = "Some outlay type";
	private static final String STORED_OUTLAY = "stored_outlay";
	private static final String FIRST_OUTLAY_TYPE = "FIRST_OUTLAY_TYPE";
	private static final String SECOND_OUTLAY_TYPE = "SECOND_OUTLAY_TYPE";
	private static final String MORE_OUTLAY_TYPES = "MORE_OUTLAY_TYPES";
	private static SharedPreferences prefs;

	public static DataStorageImpl getInstance(Context context) {
		if (ourInstance == null) {
			ourInstance = new DataStorageImpl();
		}
		initSharedPrefs(context);
		Log.i("Widget", ourInstance.toString());
		return ourInstance;
	}

	private static void initSharedPrefs(Context context) {
		prefs = context.getSharedPreferences(SHARED_STORED_DATA, Context.MODE_PRIVATE);
	}

	private DataStorageImpl() {
	}


	@Override
	public String getStoredValue() {
		return getData(STORED_VALUE, EMPTY_VALUE);
	}

	@Override
	public void saveValue(String value) {
		saveData(STORED_VALUE, value);
	}

	@Override
	public String getFirstOutlayType() {
		return getData(FIRST_OUTLAY_TYPE, EMPTY_OUTLAY);
	}

	@Override
	public String getSecondOutlayType() {
		return getData(SECOND_OUTLAY_TYPE, EMPTY_OUTLAY);
	}

	@Override
	public List<String> getOutlayTypes() {
		Set<String> outlayTypes = getData(MORE_OUTLAY_TYPES);
		if (outlayTypes == null || outlayTypes.isEmpty()) {
			return null;
		}

		return new ArrayList<>(outlayTypes);
	}

	@Override
	public void setFirstOutlayType(String type) {
		saveData(FIRST_OUTLAY_TYPE, type);
	}

	@Override
	public void setSecondOutlayType(String type) {
		saveData(SECOND_OUTLAY_TYPE, type);
	}

	@Override
	public void setOutlayTypes(List<String> types) {
		saveData(MORE_OUTLAY_TYPES, new LinkedHashSet<>(types));
	}

	@Override
	public String getSelectedOutlayType() {
		return getData(STORED_OUTLAY, EMPTY_OUTLAY);
	}

	@Override
	public void setSelectedOutlayValue(int position) {
		List<String> types = getOutlayTypes();
		if (types == null || types.isEmpty() || position >= types.size()) {
			return;
		}
		saveData(STORED_OUTLAY, types.get(position));
	}

	private void setOutlayType(int position, String type) {
		Set<String> outlayTypes = getData(MORE_OUTLAY_TYPES);
		List<String> types = new ArrayList<>(10);
		if (outlayTypes != null) {
			types.addAll(outlayTypes);
		}
		types.add(position, type);
		outlayTypes = new LinkedHashSet<>(types);
		saveData(MORE_OUTLAY_TYPES, outlayTypes);
	}

	private String getData(String key, String defValue) {
		if (prefs == null) {
			return defValue;
		}
		return prefs.getString(key, defValue);
	}

	private Set<String> getData(String key) {
		if (prefs == null) {
			return null;
		}
		return prefs.getStringSet(key, null);
	}

	private void saveData(String key, String value) {
		if (prefs == null) {
			return;
		}

		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.apply();
	}

	private void saveData(String key, Set<String> value) {
		if (prefs == null) {
			return;
		}

		SharedPreferences.Editor editor = prefs.edit();
		editor.putStringSet(key, value);
		editor.apply();
	}
}
