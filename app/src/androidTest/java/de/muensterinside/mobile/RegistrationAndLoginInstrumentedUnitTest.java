package de.muensterinside.mobile;

import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.*;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * @author Julia Bracht, Nicolas Burchert
 */
@RunWith(AndroidJUnit4.class)
public class RegistrationAndLoginInstrumentedUnitTest {
    @Rule
    public ActivityTestRule<RegistrationActivity> registrationActivityActivityTestRule =
            new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class);

    @Test
    public void testRegistrationAndLogin() throws Exception{

        // Gibt einen Usernamen ein
        onView(withId(R.id.registration_username))
                .perform(typeText("name"), closeSoftKeyboard());

        // Klickt auf den Button Registrieren
        onView(withId(R.id.registration))
                .perform(click());

        // Prüft ob der eingegebene Username wirklich übernommen wurde
        onView(withId(R.id.registration_username))
                .check(matches(withText("name")));

        // Klickt auf den Button Login
        onView(withId(R.id.login))
                .perform(click());

        /* Prüft ob der Activity Wechsel erfolgreich war,
         * da in der MainActivity kein Login Button vorhanden ist.
         */
        onView(withId(R.id.login))
                .check(doesNotExist());
    }

}
