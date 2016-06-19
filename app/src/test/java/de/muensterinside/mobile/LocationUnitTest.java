package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import de.muensterinside.mobile.entities.Category;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.entities.Vote;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationUnitTest {

    @Mock
    private Location mockLocation;

    private static final int id = 1;
    private static final String testName = "Nico";
    private static final int upInt = 2;
    private static final int downInt = 0;
    private Device device;
    private Category category;
    private static final String testLink = "Link";
    private static final int testVoteValue = 1;
    private static final String testDescription = "Beschreibung";

    @Before
    public void prepareMocks() {
        when(mockLocation.getName()).thenReturn(testName);
        when(mockLocation.getDevice()).thenReturn(device);
        when(mockLocation.getDescription()).thenReturn(testDescription);
        when(mockLocation.getCategory()).thenReturn(category);
        when(mockLocation.getLink()).thenReturn(testLink);
        when(mockLocation.getVoteValue()).thenReturn(testVoteValue);
        when(mockLocation.downVote()).thenReturn(downInt);
        when(mockLocation.upVote()).thenReturn(upInt);
        when(mockLocation.getId()).thenReturn(id);
    }

    @Test
    public void getNameTestCase() {
        String result = mockLocation.getName();
        assertEquals(result, testName);
    }

    @Test
    public void getDeviceTestCase() {
        Device result = mockLocation.getDevice();
        assertEquals(result, device);
    }

    @Test
    public void getDescriptionTestCase() {
        String result = mockLocation.getDescription();
        assertEquals(result, testDescription);
    }

    @Test
    public void getCategoryTestCase() {
        Category result = mockLocation.getCategory();
        assertEquals(result, category);
    }

    @Test
    public void getLinkTestCase() {
        String result = mockLocation.getLink();
        assertEquals(result, testLink);
    }

    @Test
    public void getVoteValueTestCase() {
        int result = mockLocation.getVoteValue();
        assertEquals(result, testVoteValue);
    }

    @Test
    public void upVoteTestCase(){
        int result = mockLocation.upVote();
        assertEquals(result, upInt);
    }

    @Test
    public void downVoteTestCase(){
        int result = mockLocation.downVote();
        assertEquals(result, downInt);
    }

    @Test
    public void setNameTestCase(){
        Mockito.doCallRealMethod().when(mockLocation).setName(testName);

        mockLocation.setName(testName);

        Mockito.verify(mockLocation).setName(testName);
    }

    @Test
    public void setDeviceTestCase(){
        Mockito.doCallRealMethod().when(mockLocation).setDevice(device);

        mockLocation.setDevice(device);

        Mockito.verify(mockLocation).setDevice(device);
    }

    @Test
    public void setDescriptionTestCase(){
        Mockito.doCallRealMethod().when(mockLocation).setDescription(testDescription);

        mockLocation.setName(testDescription);

        Mockito.verify(mockLocation).setName(testDescription);
    }

    @Test
    public void setCategoryTestCase(){
        Mockito.doCallRealMethod().when(mockLocation).setCategory(category);

        mockLocation.setCategory(category);

        Mockito.verify(mockLocation).setCategory(category);
    }

    @Test
    public void setLinkTestCase(){
        Mockito.doCallRealMethod().when(mockLocation).setLink(testLink);

        mockLocation.setLink(testLink);

        Mockito.verify(mockLocation).setLink(testLink);
    }
}
