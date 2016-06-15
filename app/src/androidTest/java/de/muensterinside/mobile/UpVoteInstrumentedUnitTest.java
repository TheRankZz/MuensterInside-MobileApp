package de.muensterinside.mobile;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class UpVoteInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class);

    @Test
    public void testVoteALocationInstrumentedUnitTest() throws Exception {


        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("Burgercult"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("Burgercult")));

        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.textViewExampleVote)).check(matches(withText("36")));
        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.textViewExampleVote)).check(matches(withText("36")));
    }
}
