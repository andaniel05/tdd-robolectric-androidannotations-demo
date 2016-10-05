package org.mockito.configuration;

/**
 * Created by ANDANIEL on 24/09/2016.
 */
public class MockitoConfiguration extends DefaultMockitoConfiguration {
    @Override
    public boolean enableClassCache() {
        return false;
    }
}
