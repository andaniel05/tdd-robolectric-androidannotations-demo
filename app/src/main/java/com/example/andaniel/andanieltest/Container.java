package com.example.andaniel.andanieltest;

import retrofit2.Retrofit;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public class Container
{
    private static Container ourInstance = new Container();

    private Retrofit retrofit;
    private ApiService apiService;

    private String email = "";
    private String password = "";

    public static Container getInstance()
    {
        return ourInstance;
    }

    public static void setInstance(Container instance)
    {
        ourInstance = instance;
    }

    private Container()
    {
    }

    public Retrofit getRetrofit()
    {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8000/")
                    .build();
        }

        return retrofit;
    }

    public ApiService getApiService()
    {
        if (null == apiService) {
            apiService = getRetrofit().create(ApiService.class);
        }

        return apiService;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void saveCredentials(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
}
