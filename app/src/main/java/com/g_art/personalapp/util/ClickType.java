package com.g_art.personalapp.util;

/**
 * PersonalApp
 * Created by fftem on 04-May-16.
 */
public enum ClickType {
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	ZERO("0"),
	PUSH(""),
	DELETE(""),
	OUTLAY_FIRST_TYPE(""),
	OUTLAY_SECOND_TYPE(""),
	OUTLAY_MORE_TYPE("");

	private String clickValue;

	ClickType(String clickValue) {
		this.clickValue = clickValue;
	}

	public void setClickValue(String clickValue) {
		this.clickValue = clickValue;
	}

	public String getClickValue() {
		return clickValue;
	}
}
