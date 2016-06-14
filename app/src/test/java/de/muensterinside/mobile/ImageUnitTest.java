package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.muensterinside.mobile.entities.Image;
import de.muensterinside.mobile.entities.Location;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageUnitTest {

    @Mock
    private Image mockImage;

    private Location location;
    private byte[] content;
    private static final String mimeType = "mimeType";

    @Before
    public void prepareMocks() {
        when(mockImage.getMimeType()).thenReturn(mimeType);
        when(mockImage.getLocation()).thenReturn(location);
        when(mockImage.getContent()).thenReturn(content);
    }

    @Test
    public void getMimeTypeTestCase(){
        String result = mockImage.getMimeType();
        assertEquals(result, mimeType);
    }

    @Test
    public void getLocationTestCase(){
        Location result = mockImage.getLocation();
        assertEquals(result, location);
    }

    @Test
    public void getContentTestCase(){
        byte[] result = mockImage.getContent();
        assertEquals(result, content);
    }

    @Test
    public void setMimeTypeTestCase(){
        Mockito.doCallRealMethod().when(mockImage).setMimeType(mimeType);

        mockImage.setMimeType(mimeType);

        Mockito.verify(mockImage).setMimeType(mimeType);
    }

    @Test
    public void setLocationTestCase(){
        Mockito.doCallRealMethod().when(mockImage).setLocation(location);

        mockImage.setLocation(location);

        Mockito.verify(mockImage).setLocation(location);
    }

    @Test
    public void setContentTestCase(){
        Mockito.doCallRealMethod().when(mockImage).setContent(content);

        mockImage.setContent(content);

        Mockito.verify(mockImage).setContent(content);
    }
}
