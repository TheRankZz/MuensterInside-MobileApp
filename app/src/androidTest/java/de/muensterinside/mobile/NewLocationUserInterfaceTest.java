package de.muensterinside.mobile;

import android.app.Activity;
import android.content.Context;
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
import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



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

    public String randomName() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

    public final String output = "Test " + randomName();

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
                .perform(typeText(output), closeSoftKeyboard());

        onView(withId(R.id.locationDescription))
                .perform(typeText("LocationDescription"), closeSoftKeyboard());

        onView(withId(R.id.locationLink))
                .perform(typeText("http://www.google.de"), closeSoftKeyboard());

        onView(withId(R.id.confirmLocation))
                .perform(click());
    }
}