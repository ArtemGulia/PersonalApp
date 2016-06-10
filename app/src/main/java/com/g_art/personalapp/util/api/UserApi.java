package com.g_art.personalapp.util.api;

import com.g_art.personalapp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.Map;

/**
 * PersonalApp
 * Created by fftem on 09-Jun-16.
 */
public interface UserApi {

	@GET("/auth/user")
	Call<User> authUser();

	@POST("/auth/android")
	Call<User> loginOrCreate(@Body Map<String, String> profile);

}
