package com.g_art.personalapp.util;

import java.util.List;

/**
 * PersonalApp
 * Created by fftem on 06-Jun-16.
 */

public interface DataStorage {

	String getStoredValue();
	void saveValue(String value);

	String getFirstOutlayType();
	String getSecondOutlayType();
	List<String> getOutlayTypes();
	void setFirstOutlayType(String type);
	void setSecondOutlayType(String type);
	void setOutlayTypes(List<String> types);

	String getSelectedOutlayType();
	void setSelectedOutlayValue(int position);

}
