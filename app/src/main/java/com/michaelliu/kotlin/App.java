package com.michaelliu.kotlin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;
import com.mdroid.lib.core.base.BaseApp;
import com.michaelliu.kotlin.utils.ImageLoader;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Description:
 *
 * <p>Created by liuguoquan on 2017/11/3 17:23.
 */
public class App extends BaseApp {

    private static final String TAG = "lgq";
    private static App mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoader.init(this);
        FormatStrategy strategy = PrettyFormatStrategy.newBuilder().tag("lgq").build();
        Logger.addLogAdapter(
                new AndroidLogAdapter(strategy) {
                    @Override
                    public boolean isLoggable(int priority, String tag) {
                        return BuildConfig.DEBUG;
                    }
                });
        registerActivityLifecycleCallbacks(
                new ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                        Log.d(TAG, "onActivityCreated: " + activity.getClass().getName());
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                        Log.d(TAG, "onActivityStarted: ");
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        Log.d(TAG, "onActivityResumed: ");
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                        Log.d(TAG, "onActivityPaused: ");
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                        Log.d(TAG, "onActivityStopped: ");
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                        Log.d(TAG, "onActivitySaveInstanceState: ");
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        Log.d(TAG, "onActivityDestroyed: ");
                    }
                });
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
