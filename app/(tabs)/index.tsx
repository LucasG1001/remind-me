import { useRouter } from "expo-router";
import { FlatList, Pressable, StyleSheet, View } from "react-native";

import { ThemedText } from "@/components/themed-text";
import { ThemedView } from "@/components/themed-view";
import { useEffect, useState } from "react";
import { Calendar } from "react-native-calendars";
import useReminderDatabase, {
  ReminderDatabase,
} from "../database/useReminderDatabase";

export default function HomeScreen() {
  const router = useRouter();
  const [remiders, setReminders] = useState<ReminderDatabase[]>([]);
  const { get } = useReminderDatabase();

  useEffect(() => {
    const getReminders = async () => {
      const reminders = await get();
      setReminders(reminders);
    };

    getReminders();
  }, []);

  return (
    <ThemedView style={{ flex: 1 }}>
      <View style={{ padding: 16, backgroundColor: "#000" }}>
        <Calendar
          theme={{
            backgroundColor: "#000",
            calendarBackground: "#000",

            textSectionTitleColor: "#aaa",
            dayTextColor: "#fff",
            monthTextColor: "#fff",

            selectedDayBackgroundColor: "#1e90ff",
            selectedDayTextColor: "#fff",

            todayTextColor: "#1e90ff",
            arrowColor: "#fff",

            textDisabledColor: "#555",
          }}
        />
      </View>

      <View style={{ flex: 1 }}>
        <FlatList
          data={remiders.slice(0, 3)}
          keyExtractor={(item) => String(item.id)}
          contentContainerStyle={{ padding: 16, gap: 12 }}
          renderItem={({ item }) => (
            <View
              style={{
                padding: 16,
                borderRadius: 12,
                backgroundColor: "#1e1e1e",
              }}
            >
              <ThemedText style={{ fontSize: 16, fontWeight: "bold" }}>
                {item.title}
              </ThemedText>

              <ThemedText style={{ color: "#aaa" }}>
                {item.description}
              </ThemedText>
            </View>
          )}
        />
      </View>
      <Pressable
        style={styles.fab}
        onPress={() => router.push("/ReminderForm")}
      >
        <ThemedText style={styles.fabText}>Nome Lembrete</ThemedText>
      </Pressable>
    </ThemedView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  fab: {
    position: "absolute",
    bottom: 24,
    right: 24,
    backgroundColor: "#4CAF50",
    paddingHorizontal: 20,
    paddingVertical: 14,
    borderRadius: 32,
    elevation: 5,
  },
  fabText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
});
