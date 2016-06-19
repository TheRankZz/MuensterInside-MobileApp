package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import de.muensterinside.mobile.entities.Device;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(MockitoJUnitRunner.class)
public class DeviceUnitTest {

    @Mock
    private Device mockDevice;

    private static final int id = 1;
    private static final String testName = "Nico";
    private static final String testAndroidID = "AndroidID";

    @Before
    public void prepareMocks() {
        when(mockDevice.getUsername()).thenReturn(testName);
        when(mockDevice.getId()).thenReturn(id);
        when(mockDevice.getAndroidUuid()).thenReturn(testAndroidID);
    }

    @Test
    public void getIdTestCase() {
        int result = mockDevice.getId();
        assertEquals(result, id);
    }

    @Test
    public void getAndroidIDTestCase() {
        String result = mockDevice.getAndroidUuid();
        assertEquals(result, testAndroidID);
    }

    @Test
    public void setUsernameTestCase(){
        Mockito.doCallRealMethod().when(mockDevice).setUsername(testName);

        mockDevice.setUsername(testName);

        Mockito.verify(mockDevice).setUsername(testName);
    }

    @Test
    public void setIdTestCase(){
        Mockito.doCallRealMethod().when(mockDevice).setId(id);

        mockDevice.setId(id);

        Mockito.verify(mockDevice).setId(id);
    }

    @Test
    public void setAndroidIDTestCase(){
        Mockito.doCallRealMethod().when(mockDevice).setAndroidUuid(testAndroidID);

        mockDevice.setAndroidUuid(testAndroidID);

        Mockito.verify(mockDevice).setAndroidUuid(testAndroidID);
    }
}
