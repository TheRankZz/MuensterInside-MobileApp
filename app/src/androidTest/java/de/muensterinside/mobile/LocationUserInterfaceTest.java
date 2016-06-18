package de.muensterinside.mobile;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.*;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.*;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringStartsWith.startsWith;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocationUserInterfaceTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void test1Register(){
        Activity activity = rule.getActivity();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("androidId", "MeineAndroidUuid1");
        editor.putString("username", "User");
        editor.putInt("deviceId", 1);
        editor.putBoolean("test", true);
        editor.apply();
    }

    @Test
    public void test2ShowALocationTest() throws Exception {

        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("PierHouse"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("PierHouse")));
    }

    @Test
    public void test3UpVoteALocationTest() throws Exception {

        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("PierHouse"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("PierHouse")));

        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.up)).perform(click());
        onView(withId(R.id.up)).check(matches(not(isEnabled())));
    }

    @Test
    public void test4WriteCommentTest() throws Exception{

        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("PierHouse"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("PierHouse")));

        onView(withId(R.id.button1)).perform(scrollTo(), click());
        onView(withId(R.id.editText)).perform(clearText()).perform(typeText("Kommentar"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.KommentarAnzeigen)).perform(scrollTo(), click());
        onView(withId(R.id.commentList)).check(matches(isDisplayed()));
    }
}