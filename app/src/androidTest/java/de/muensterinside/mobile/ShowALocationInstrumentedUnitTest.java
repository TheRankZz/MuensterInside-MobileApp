package de.muensterinside.mobile;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.*;
import android.widget.TextView;

import java.util.HashMap;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Julia Bracht and Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class ShowALocationInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<MainActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testShowALocationInstrumentedUnitTest() throws Exception {
        onData(allOf(is(instanceOf(String.class)), is("Essen"))).perform(click());


        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("Extrablatt"))))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("Extrablatt")));

    }

}
