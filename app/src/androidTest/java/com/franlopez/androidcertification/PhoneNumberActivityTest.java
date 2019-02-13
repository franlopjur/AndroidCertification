package com.franlopez.androidcertification;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.franlopez.androidcertification.ui.phonenumber.PhoneNumberActivity;
import com.franlopez.androidcertification.ui.uitest.FirstActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class PhoneNumberActivityTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            PhoneNumberActivity.class);

    @Test
    public void navigateFirstActivityToSecond() {
        String[] myArray =
                activityRule.getActivity().getResources()
                        .getStringArray(R.array.labels_array);

        for (int i = 0; i < myArray.length; i++) {
            onView(withId(R.id.label_spinner)).perform(click());
            onData(is(myArray[i])).perform(click());
            onView(withId(R.id.text_phonelabel)).check(matches(withText(containsString(myArray[i]))));
        }
    }
}
