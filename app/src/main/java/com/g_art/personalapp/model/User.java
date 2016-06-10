package com.g_art.personalapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * PersonalApp
 * Created by fftem on 09-Jun-16.
 */
public class User implements Parcelable {
	private String displayName;
	private String provider;
	private String providerId;

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public User() {
	}

	public User(String displayName, String provider, String providerId) {
		this.displayName = displayName;
		this.provider = provider;
		this.providerId = providerId;
	}

	public User(Parcel in) {
		this.displayName = in.readString();
		this.provider = in.readString();
		this.providerId = in.readString();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(displayName);
		dest.writeString(provider);
		dest.writeString(providerId);
	}
}
