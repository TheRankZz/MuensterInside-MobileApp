package de.muensterinside.mobile;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by Julia Bracht and Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class NewCommentInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<LocationActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<LocationActivity>(LocationActivity.class);

    @Test
    public void testVoteALocationInstrumentedUnitTest() throws Exception {

        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.editText)).perform(clearText()).perform(typeText("Neuer Kommentar"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.KommentarAnzeigen)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Neuer Kommentar"))).check(matches(isDisplayed()));
    }
}
