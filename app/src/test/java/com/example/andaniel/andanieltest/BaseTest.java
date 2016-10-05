package com.example.andaniel.andanieltest;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public abstract class BaseTest
{
    String email = "user1@localhost.com";
    String password = "user1";

    public Container getMockedContainer()
    {
        Container container = mock(Container.class);
        Container.setInstance(container);

        return container;
    }
}
