package com.getext;

import androidx.multidex.MultiDexApplication;

import com.getext.keys.AppKeys;
import com.getext.utils.logger.Logger;
import com.getext.utils.preferences.SharedPreferenceHelper;

public class GetextApplication extends MultiDexApplication {
    private final String TAG = GetextApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG, "Firebase Token: " + SharedPreferenceHelper.getString(this, AppKeys.FCM_TOKEN, null));
    }
}