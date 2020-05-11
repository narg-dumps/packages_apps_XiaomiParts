package com.corvus.parts;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceManager;

public class DimmerSwitch implements OnPreferenceChangeListener {

    private static final String KEY = DeviceSettings.PREF_BACKLIGHT_DIMMER;
    private static final String FILE = "/sys/module/mdss_fb/parameters/backlight_dimmer";

    public static boolean isSupported(Context context) {
        return Utils.fileWritable(FILE);
    }

    public static void restore(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (sharedPrefs.contains(KEY))
            writeValue(context, sharedPrefs.getBoolean(KEY, false));
    }

    public static boolean readValue(Context context) {
        return !Utils.readValue(FILE, "0").equals("0");
    }

    public static void writeValue(Context context, boolean newValue) {
        Utils.writeValue(FILE, (newValue ? "1" : "0"));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        writeValue(preference.getContext(), (Boolean) newValue);
        return true;
    }
}
