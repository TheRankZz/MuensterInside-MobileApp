package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentUnitTest {

    @Mock
    private Comment mockComment;

    private static final int id = 1;
    private static final String testText = "Text";
    private Location location;
    private Device device;

    @Before
    public void prepareMocks() {
        when(mockComment.getText()).thenReturn(testText);
        when(mockComment.getLocation()).thenReturn(location);
        when(mockComment.getDevice()).thenReturn(device);
    }

    @Test
    public void getTextTestCase() {
        String result = mockComment.getText();
        assertEquals(result, testText);
    }

    @Test
    public void getLocationTestCase(){
        Location result = mockComment.getLocation();
        assertEquals(result, location);
    }
    @Test
    public void getDeviceTestCase(){
        Device result = mockComment.getDevice();
        assertEquals(result, device);
    }

    @Test
    public void setUsernameTestCase(){
        Mockito.doCallRealMethod().when(mockComment).setText(testText);

        mockComment.setText(testText);

        Mockito.verify(mockComment).setText(testText);
    }

    @Test
    public void setDeviceTestCase(){
        Mockito.doCallRealMethod().when(mockComment).setDevice(device);

        mockComment.setDevice(device);

        Mockito.verify(mockComment).setDevice(device);
    }

    @Test
    public void setLocationTestCase(){
        Mockito.doCallRealMethod().when(mockComment).setLocation(location);

        mockComment.setLocation(location);

        Mockito.verify(mockComment).setLocation(location);
    }
}
