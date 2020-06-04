LOCAL_PATH := $(call my-dir)

ifeq ($(TARGET_DEVICE),$(filter $(TARGET_DEVICE),lavender mido wayne whyred platina ginkgo vince twolip ysl land))
include $(call all-makefiles-under,$(LOCAL_PATH))
endif
