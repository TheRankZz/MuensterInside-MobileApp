package de.muensterinside.mobile;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;




/**
 * Created by Julia Bracht and Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class UpVoteInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<LocationActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<LocationActivity>(LocationActivity.class);

    @Test
    public void testVoteALocationInstrumentedUnitTest() throws Exception {

        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.textViewExampleVote)).check(matches(withText("11")));
        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.textViewExampleVote)).check(matches(withText("11")));
    }
}
