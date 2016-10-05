package com.example.andaniel.andanieltest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public class LoginActivityImpl extends  AbstractActivityImpl
{
    public LoginActivityImpl(Activity activity)
    {
        this.activity = activity;
    }

    public void login(String email, String password) throws Exception
    {
        ApiService apiService = Container.getInstance().getApiService();
        Call<ResponseBody> call = apiService.user(email, password);
        Response<ResponseBody> response = call.execute();

        if (200 == response.code() || 201 == response.code()) {
            processLoginSuccess(email, password);
        } else {
            processLoginFailed();
        }
    }

    public void processLoginFailed()
    {
        Toast.makeText(activity, activity.getResources().getString(R.string.login_failed_message), Toast.LENGTH_SHORT).show();
    }

    public void processLoginSuccess(String email, String password)
    {
        Container.getInstance().saveCredentials(email, password);

        Intent intent = new Intent(activity, MainActivity_.class);
        activity.startActivity(intent);
    }
}
