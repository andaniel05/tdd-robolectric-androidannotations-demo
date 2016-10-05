package com.example.andaniel.andanieltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity
{
    protected LoginActivityImpl impl;

    @ViewById(R.id.input_email)
    EditText inputEmail;

    @ViewById(R.id.input_password)
    EditText inputPassword;

    @Click
    void button_login()
    {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        try {
            impl.login(email, password);
        } catch (Exception e) {}
    }

    public LoginActivityImpl getImplementation()
    {
        return impl;
    }

    public void setImplementation(LoginActivityImpl impl)
    {
        this.impl = impl;
    }
}
