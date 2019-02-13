package com.franlopez.androidcertification;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.franlopez.androidcertification.manager.Calculator;
import com.franlopez.androidcertification.ui.calculator.CalculatorActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/tesq2ting">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorActivityTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            CalculatorActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void addTwoNumbers() {
        onView(withId(R.id.operand_one_edit_text))
                .perform(clearText(), typeText("1"));
        onView(withId(R.id.operand_two_edit_text))
                .perform(clearText(), typeText("1"));
        onView(withId(R.id.operation_add_btn))
                .perform(click());
        onView(withId(R.id.operation_result_text_view))
                .check(matches(withText(containsString("2"))));
    }
}
