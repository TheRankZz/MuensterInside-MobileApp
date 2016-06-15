package de.muensterinside.mobile;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

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
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class NewCommentInstrumentedUnitTest {

    @Rule
    public ActivityTestRule<MainActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testVoteALocationInstrumentedUnitTest() throws Exception {


        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("Burger Cult"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("Burger Cult")));

        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.editText)).perform(clearText()).perform(typeText("Kommentar"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.KommentarAnzeigen)).perform(click());
        onView(withId(R.id.commentList)).check(matches(isDisplayed()));
    }
}
