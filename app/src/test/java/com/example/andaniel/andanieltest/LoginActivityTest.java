package com.example.andaniel.andanieltest;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import android.app.Activity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginActivityTest extends BaseTest
{
    @Test
    public void clickOnLoginButtonInvokeImplLogin() throws Exception
    {
        // Arrange
        LoginActivity_ activity = Robolectric.setupActivity(LoginActivity_.class);
        LoginActivityImpl impl = mock(LoginActivityImpl.class);

        impl.setActivity((Activity) activity);
        activity.setImplementation(impl);

        Button loginButton = (Button) activity.findViewById(R.id.button_login);
        EditText inputEmail = (EditText) activity.findViewById(R.id.input_email);
        EditText inputPassword = (EditText) activity.findViewById(R.id.input_password);

        inputEmail.setText(email);
        inputPassword.setText(password);

        // Act
        loginButton.performClick();

        // Asserts
        verify(impl).login(email, password);
    }
}
