package com.example.andaniel.andanieltest;

import org.junit.Test;

import retrofit2.Retrofit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public class ContainerTest extends BaseTest
{
    @Test
    public void getRetrofit() throws Exception
    {
        Retrofit retrofit = Container.getInstance().getRetrofit();
        Retrofit retrofit1 = Container.getInstance().getRetrofit();

        assertTrue(retrofit instanceof Retrofit);
        assertSame(retrofit, retrofit1);
    }

    @Test
    public void getApiService() throws Exception
    {
        ApiService service = Container.getInstance().getApiService();
        ApiService service1 = Container.getInstance().getApiService();

        assertTrue(service instanceof ApiService);
        assertSame(service, service1);
    }

    @Test
    public void saveCredentials() throws Exception
    {
        Container container = Container.getInstance();

        container.saveCredentials(email, password);

        assertEquals(email, container.getEmail());
        assertEquals(password, container.getPassword());
    }
}
