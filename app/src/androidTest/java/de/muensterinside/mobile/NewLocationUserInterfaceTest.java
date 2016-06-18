package de.muensterinside.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class NewLocationUserInterfaceTest {

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
    public void test2NewLocationTest() throws Exception {

        onView(withId(R.id.newLocation))
                .perform(click());

        onView(withId(R.id.locationName))
                .perform(typeText("LocationTest"), closeSoftKeyboard());

        onView(withId(R.id.locationDescription))
                .perform(typeText("LocationDescription"), closeSoftKeyboard());

        onView(withId(R.id.locationLink))
                .perform(typeText("LocationLink"), closeSoftKeyboard());

        onView(withId(R.id.confirmLocation))
                .perform(click());
    }

    @Test
    public void test3ShowNewLocationTest() throws Exception{

        onData(allOf(is(instanceOf(HashMap.class)), hasEntry(equalTo("First"), is("LocationTest"))))
                .inAdapterView(withId(R.id.categoryList))
                .perform(click());

        onView(withId(R.id.textViewExampleName))
                .check(matches(withText("LocationTest")));
    }
}