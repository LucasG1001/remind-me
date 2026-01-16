import { useSQLiteContext } from "expo-sqlite";

export type ReminderDatabase = {
  id?: number;
  type: string;
  title: string;
  description?: string;
  active?: number;
};

export default function useReminderDatabase() {
  const database = useSQLiteContext();

  async function create(reminder: ReminderDatabase) {
    const statement = await database.prepareAsync(`
            INSERT INTO reminders (type, title, description, is_active)
            VALUES($type, $title, $description, $active);
        `);

    try {
      const result = await statement.executeAsync({
        $type: reminder.type,
        $title: reminder.title,
        $description: reminder.description ?? null,
        $active: reminder.active ?? 1,
      });

      const insertedRowId = result.lastInsertRowId;

      return { insertedRowId };
    } catch (error) {
      console.log(error);
    } finally {
      await statement.finalizeAsync();
    }
  }

  async function get() {
    const rows = await database.getAllAsync<ReminderDatabase>(`
            SELECT *
            FROM reminders
            ORDER BY id DESC
            `);

    return rows;
  }

  return { create, get };
}
