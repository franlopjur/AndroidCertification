package com.franlopez.androidcertification;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.franlopez.androidcertification.ui.calculator.CalculatorActivity;
import com.franlopez.androidcertification.ui.uitest.FirstActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FirstActivityTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            FirstActivity.class);

    @Test
    public void navigateFirstActivityToSecond() {
        onView(withId(R.id.editText_main)).perform(typeText("Test"));
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_message)).check(matches(isDisplayed()));
        onView(withId(R.id.text_message)).check(matches(withText("Test")));
    }
}
