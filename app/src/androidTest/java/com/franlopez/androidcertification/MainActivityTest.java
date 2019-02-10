package com.franlopez.androidcertification;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.franlopez.androidcertification.db.MyDatabase;
import com.franlopez.androidcertification.ui.main.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MyDatabase db;

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(appContext, MyDatabase.class).build();
    }

    @After
    public void close() {
        Collection<IdlingResource> resourceList = IdlingRegistry.getInstance().getResources();
        for (IdlingResource resource : resourceList) {
            IdlingRegistry.getInstance().unregister(resource);
        }
    }

    @Test
    public void simulateCallService() throws InterruptedException {
        String textToSearch = "android";
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.main__list__elements);
        //IdlingRegistry.getInstance().register(new ViewVisibilityIdlingResource(recyclerView, View.VISIBLE));

        onView(withId(R.id.main__input__query))
                .perform(clearText(), typeText(textToSearch))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.main__list__elements))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText(containsString(textToSearch)))));
    }
}
