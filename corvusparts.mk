# Overlays
DEVICE_PACKAGE_OVERLAYS += $(LOCAL_PATH)/overlay

# CorvusParts packages
PRODUCT_PACKAGES += \
    CorvusParts

BOARD_VENDOR_SEPOLICY_DIRS += $(LOCAL_PATH)/sepolicy/vendor
