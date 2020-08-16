# if u want use it, clone https://github.com/narg-dump/packages_apps_XiaomiParts.git packages/apps/XiaomiParts
# add inherit on your dt $(call inherit-product, packages/apps/XiaomiParts/xiaomiparts.mk)
# enforcing build, add on sepolicy/vendor :
```
file.te : 
# XiaomiParts	
type kcal_dev, sysfs_type, fs_type;
type sysfs_fpsinfo, sysfs_type, fs_type;
type sysfs_torch, sysfs_type, fs_type;

file_contexts :
# XiaomiParts	
/sys/devices/platform/kcal_ctrl.0(/.*)?                      u:object_r:kcal_dev:s0
/sys/bus/platform/drivers/kcal_ctrl(/.*)?                    u:object_r:kcal_dev:s0
/sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_0/max_brightness   u:object_r:sysfs_torch:s0
/sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_1/max_brightness   u:object_r:sysfs_torch:s0
/sys/class/graphics/fb0/measured_fps                         u:object_r:sysfs_fpsinfo:s0

system_app.te :
allow system_app kcal_dev:file rw_file_perms;
allow system_app kcal_dev:dir search;
allow system_app sysfs_vibrator:file rw_file_perms;
allow system_app sysfs_vibrator:dir search;
allow system_app sysfs_fpsinfo:file { open read getattr write };	
allow system_app sysfs_torch:file { open getattr write };
```

# Other add :
```
device.mk :
init.xiaomi.rc \

rootdir/etc/init.xiaomi.rc :
# Copyright (c) 2020, The Linux Foundation. All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#     * Redistributions of source code must retain the above copyright
#       notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above
#       copyright notice, this list of conditions and the following
#       disclaimer in the documentation and/or other materials provided
#       with the distribution.
#     * Neither the name of The Linux Foundation nor the names of its
#       contributors may be used to endorse or promote products derived
#       from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
# WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
# ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
# BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
# BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
# WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
# OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
# IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
#

on boot

    # Vibration
    chown system system /sys/devices/virtual/timed_output/vibrator/vtg_level
    chmod 0660 /sys/devices/virtual/timed_output/vibrator/vtg_level

    # KCal
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_cont
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_enable
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_hue
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_sat
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_val
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal_min
    chown system system /sys/devices/platform/kcal_ctrl.0/kcal
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_cont
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_enable
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_hue
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_sat
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_val
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal_min
    chmod 660 /sys/devices/platform/kcal_ctrl.0/kcal

    # Torch
    chmod 0660 /sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_0/max_brightness
    chmod 0660 /sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_1/max_brightness
    chown system system /sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_0/max_brightness
    chown system system /sys/devices/soc/800f000.qcom,spmi/spmi-0/spmi0-03/800f000.qcom,spmi:qcom,pm660l@3:qcom,leds@d300/leds/led:torch_1/max_brightness

    # Backlight dimmer
    chown system system /sys/module/mdss_fb/parameters/backlight_dimmer
    chmod 0660 /sys/module/mdss_fb/parameters/backlight_dimmer

    # FPS Info
    chown system graphics /sys/class/graphics/fb0/measured_fps
    chmod 0666 /sys/class/graphics/fb0/measured_fps

rootdir/etc/init.qcom.rc :
import /vendor/etc/init/hw/init.xiaomi.rc

rootdir/Android.mk :
include $(CLEAR_VARS)
LOCAL_MODULE       := init.xiaomi.rc
LOCAL_MODULE_TAGS  := optional
LOCAL_MODULE_CLASS := ETC
LOCAL_SRC_FILES    := etc/init.xiaomi.rc
LOCAL_MODULE_PATH  := $(TARGET_OUT_VENDOR_ETC)/init/hw
include $(BUILD_PREBUILT)
```
# ENJOY !!
