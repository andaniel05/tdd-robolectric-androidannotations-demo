package com.example.andaniel.andanieltest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import android.content.Context;
import android.content.Intent;

import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({Response.class, Call.class})
public class LoginActivityImplTest extends BaseTest
{
    @Rule
    public PowerMockRule rule = new PowerMockRule();

    LoginActivity_ activity;
    LoginActivityImpl impl, spyImpl;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.setupActivity(LoginActivity_.class);
        impl = new LoginActivityImpl(activity);
        spyImpl = spy(impl);
    }

    @Test
    public void processLoginFailed() throws Exception
    {
        // Arrange
        final Context context = RuntimeEnvironment.application;

        // Act
        impl.processLoginFailed();

        // Asserts
        assertEquals(context.getString(R.string.login_failed_message), ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void processLoginSuccess() throws Exception
    {
        // Arrange
        Container container = getMockedContainer();

        // Act
        impl.processLoginSuccess(email, password);

        // Asserts
        Intent expectedIntent = new Intent(activity, MainActivity_.class);
        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();

        assertTrue(intent.filterEquals(expectedIntent));
        verify(container).saveCredentials(email, password);
    }

    public Call<ResponseBody> getMockedCall(int code) throws Exception
    {
        Response<ResponseBody> response = PowerMockito.mock(Response.class);
        when(response.code()).thenReturn(code);

        Call<ResponseBody> call = PowerMockito.mock(Call.class);
        when(call.execute()).thenReturn(response);

        return call;
    }

    @Test
    public void processLoginSuccess_On200ResponseCode() throws Exception
    {
        // Arrange
        Call<ResponseBody> mockedCall = getMockedCall(200);

        ApiService apiService = mock(ApiService.class);
        when(apiService.user(email, password)).thenReturn(mockedCall);

        Container container = getMockedContainer();
        when(container.getApiService()).thenReturn(apiService);

        // Act
        spyImpl.login(email, password);

        // Asserts
        verify(spyImpl).processLoginSuccess(email, password);
    }

    @Test
    public void processLoginSuccess_On201ResponseCode() throws Exception
    {
        // Arrange
        Call<ResponseBody> mockedCall = getMockedCall(201);

        ApiService apiService = mock(ApiService.class);
        when(apiService.user(email, password)).thenReturn(mockedCall);

        Container container = getMockedContainer();
        when(container.getApiService()).thenReturn(apiService);

        // Act
        spyImpl.login(email, password);

        // Asserts
        verify(spyImpl).processLoginSuccess(email, password);
    }

    @Test
    public void processLoginFailed_OnOthersResponseCode() throws Exception
    {
        // Arrange
        Call<ResponseBody> mockedCall = getMockedCall(401);

        ApiService apiService = mock(ApiService.class);
        when(apiService.user(email, password)).thenReturn(mockedCall);

        Container container = getMockedContainer();
        when(container.getApiService()).thenReturn(apiService);

        // Act
        spyImpl.login(email, password);

        // Asserts
        verify(spyImpl).processLoginFailed();
    }
}