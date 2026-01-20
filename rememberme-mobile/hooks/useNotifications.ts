import * as Notifications from "expo-notifications";
import { useCallback, useEffect } from "react";

export function useNotifications() {
  useEffect(() => {
    Notifications.requestPermissionsAsync();
  }, []);

  const sendMessage = useCallback(
    async (
      content: Notifications.NotificationContentInput,
      trigger: Notifications.NotificationTriggerInput
    ) => {
      return Notifications.scheduleNotificationAsync({
        content,
        trigger,
      });
    },
    []
  );

  const cancel = useCallback(async (id: string) => {
    await Notifications.cancelScheduledNotificationAsync(id);
  }, []);

  return {
    sendMessage,
    cancel,
  };
}
