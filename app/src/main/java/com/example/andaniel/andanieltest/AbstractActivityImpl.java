package com.example.andaniel.andanieltest;

import android.app.Activity;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public abstract class AbstractActivityImpl
{
    protected Activity activity;

    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }
}