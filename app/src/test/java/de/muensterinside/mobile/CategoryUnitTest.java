package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.muensterinside.mobile.entities.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryUnitTest {

    @Mock
    private Category mockCategory;

    List<Location> locationList = new ArrayList<Location>();
    private static final int id = 1;
    private static final String testName = "Nico";


    @Before
    public void prepareMocks() {
        when(mockCategory.getName()).thenReturn(testName);
        when(mockCategory.getId()).thenReturn(0);
        when(mockCategory.getLocations()).thenReturn(locationList);
    }

    @Test
    public void getNameTestCase() {
        String result = mockCategory.getName();
        assertEquals(result, testName);
    }

    @Test
    public void getLocationsTestCase() {
        List<Location> result = mockCategory.getLocations();
        assertEquals(result, locationList);
    }

    @Test
    public void setIdTestCase(){
        Mockito.doCallRealMethod().when(mockCategory).setId(id);

        mockCategory.setId(id);

        Mockito.verify(mockCategory).setId(id);
    }

    @Test
    public void setNameTestCase(){
        Mockito.doCallRealMethod().when(mockCategory).setName(testName);

        mockCategory.setName(testName);

        Mockito.verify(mockCategory).setName(testName);
    }
}

