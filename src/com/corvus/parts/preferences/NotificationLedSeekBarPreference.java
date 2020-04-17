package com.corvus.parts.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.corvus.parts.FileUtils;

public class NotificationLedSeekBarPreference extends CustomSeekBarPreference {

    public static final String NOTIF_LED_PATH = "/sys/class/leds/red/brightness";

    public NotificationLedSeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static void setValue(Context context, String newValue) {
        FileUtils.setValue(NOTIF_LED_PATH, newValue);
    }

    public static void saveValue(Context context, String newValue) {
        setValue(context, newValue);
    }

    @Override
    protected void changeValue(int newValue) {
        saveValue(getContext(), String.valueOf(newValue));
    }
} 
