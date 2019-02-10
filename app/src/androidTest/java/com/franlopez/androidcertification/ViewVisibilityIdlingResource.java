package com.franlopez.androidcertification;

import android.support.test.espresso.IdlingResource;
import android.view.View;

public class ViewVisibilityIdlingResource implements IdlingResource {

    private View view;
    private int expectedVisibility;
    private boolean idle;
    private ResourceCallback resourceCallback;

    public ViewVisibilityIdlingResource(View view, int expectedVisibility) {
        this.view = view;
        this.expectedVisibility = expectedVisibility;
        this.idle = false;
        this.resourceCallback = null;
    }

    @Override
    public final String getName() {
        return ViewVisibilityIdlingResource.class.getSimpleName();
    }

    @Override
    public final boolean isIdleNow() {
        idle = idle ||
                view.getVisibility() == expectedVisibility;

        if (idle) {
            if (resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
