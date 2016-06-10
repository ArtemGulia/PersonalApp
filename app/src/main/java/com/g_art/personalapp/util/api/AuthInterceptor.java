package com.g_art.personalapp.util.api;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * PersonalApp
 * Created by fftem on 09-Jun-16.
 */
public class AuthInterceptor implements Interceptor {
	public static final String COOKIE = "COOKIE";
	private String sSessionId;

	public AuthInterceptor(String sSessionId) {
		this.sSessionId = sSessionId;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {Request original = chain.request();
		Request.Builder requestBuilder = original.newBuilder()
				.header(COOKIE, sSessionId)
				.header("Accept", "application/json")
				.method(original.method(), original.body());

		Request request = requestBuilder.build();
		return chain.proceed(request);
	}

	public String getsSessionId() {
		return sSessionId;
	}

	public void setsSessionId(String sSessionId) {
		this.sSessionId = sSessionId;
	}
}
