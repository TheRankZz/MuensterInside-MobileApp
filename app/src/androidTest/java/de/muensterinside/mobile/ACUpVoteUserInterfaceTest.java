package de.muensterinside.mobile;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ACUpVoteUserInterfaceTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> upVoteTestRule =
            new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class);

    @Test
    public void testVoteALocationInstrumentedUnitTest() throws Exception {

        onView(withId(R.id.button2)).perform(click());

        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("Burger Cult"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("Burger Cult")));

        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.up)).check(matches(not(isEnabled())));
    }
}