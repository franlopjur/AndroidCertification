package com.franlopez.androidcertification;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.franlopez.androidcertification.ui.notification.NotificationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NotificationActivityTest {

    @Rule
    public ActivityTestRule notificationActivityRule = new ActivityTestRule<>(NotificationActivity.class);

    private UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Before
    public void setUp() {

    }

    @Test
    public void notificationWasShow() {
        //- Limpiamos todas las notificaciones y pulsamos back
        uiDevice.openNotification();
        //- "clear_all" es el recurso que tiene el botón de limpiar todas las notificaciones
        UiObject2 clearAllNotifications = uiDevice
                .findObject(By.res("com.android.systemui", "clear_all"));
        clearAllNotifications.click();
        uiDevice.pressBack();

        //- Creamos una notificación
        onView(withId(R.id.notification__btn__notify_me)).perform(click());

        //- Abrimos las notificaciones, buscamos la que tenga el texto "Prueba" y hacemos click sobre ella
        uiDevice.openNotification();
        UiObject2 notification = uiDevice.findObject(By.textContains("Prueba"));
        notification.click();

        //- Después de abrir la notificación se debe ver esta vista que está en la MainActivity
        uiDevice.wait(Until.hasObject(By.textStartsWith("Hello")), 2000);
        onView(withId(R.id.main__input__query)).check(matches(isDisplayed()));
    }
}
