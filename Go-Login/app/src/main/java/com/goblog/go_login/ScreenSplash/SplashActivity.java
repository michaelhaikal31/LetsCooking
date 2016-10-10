package com.goblog.go_login.ScreenSplash;

import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;

import com.goblog.go_login.activity.HomeApp;

/**
 * Created by haikal on 9/24/2016.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(new Intent(this, HomeApp.class))
                .addNextIntent(new Intent(this, IntroActivity.class))
                .startActivities();
    }
}
