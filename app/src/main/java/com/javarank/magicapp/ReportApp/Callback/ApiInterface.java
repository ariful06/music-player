package com.javarank.magicapp.ReportApp.Callback;


import com.javarank.magicapp.ReportApp.Model.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("signup.php")
    Call<Users> performRegistration(@Query("user_name") String Name, @Query("user_email")  String UserEmail, @Query("user_password")  String UserPassword);

    @GET("login.php")
    Call<Users> performLogin(@Query("user_email")  String UserEmail, @Query("user_password")  String UserPassword);

}
