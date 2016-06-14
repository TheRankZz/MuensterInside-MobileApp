package de.muensterinside.mobile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.muensterinside.mobile.entities.Device;
import de.muensterinside.mobile.entities.Location;
import de.muensterinside.mobile.entities.Vote;
import de.muensterinside.mobile.entities.VoteType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(MockitoJUnitRunner.class)
public class VoteUnitTest {

    @Mock
    private Vote mockVote;

    private VoteType voteType;
    private Location location;
    private Device device;

    @Before
    public void prepareMocks() {
        when(mockVote.getType()).thenReturn(voteType);
        when(mockVote.getLocation()).thenReturn(location);
        when(mockVote.getDevice()).thenReturn(device);
    }

    @Test
    public void getTypeTestCase(){
        VoteType result = mockVote.getType();
        assertEquals(result, voteType);
    }

    @Test
    public void getLocationTestCase(){
        Location result = mockVote.getLocation();
        assertEquals(result, location);
    }

    @Test
    public void getDeviceTestCase(){
        Device result = mockVote.getDevice();
        assertEquals(result, device);
    }

    @Test
    public void setVoteTypeTestCase(){
        Mockito.doCallRealMethod().when(mockVote).setType(voteType);

        mockVote.setType(voteType);

        Mockito.verify(mockVote).setType(voteType);
    }

    @Test
    public void setLocationTestCase(){
        Mockito.doCallRealMethod().when(mockVote).setLocation(location);

        mockVote.setLocation(location);

        Mockito.verify(mockVote).setLocation(location);
    }

    @Test
    public void setDeviceTestCase(){
        Mockito.doCallRealMethod().when(mockVote).setDevice(device);

        mockVote.setDevice(device);

        Mockito.verify(mockVote).setDevice(device);
    }
}
