package com.g_art.personalapp.util.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * PersonalApp
 * Created by fftem on 09-Jun-16.
 */
public class RestClient {
	private static final String API_BASE_URL = "";

//	private static String sSessionId;

	private static Gson gson = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static Retrofit.Builder builder = new Retrofit.Builder()
			.baseUrl(API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson));

	public static UserApi getUserApi(String sSessionId) {
		return createService(UserApi.class, sSessionId);
	}

	public static OutlayApi getOutlayApi(String sSessionId) {
		return createService(OutlayApi.class, sSessionId);
	}


	public static <S> S createService(Class<S> serviceClass, String sSessionId) {
		if (sSessionId != null) {
			httpClient.addInterceptor(new AuthInterceptor(sSessionId));
		}

		OkHttpClient client = httpClient.build();
		Retrofit retrofit = builder.client(client).build();
		return retrofit.create(serviceClass);
	}
//
//	public static String getsSessionId() {
//		return sSessionId;
//	}
//
//	public static void setsSessionId(String sSessionId) {
//		RestClient.sSessionId = sSessionId;
//	}
}
