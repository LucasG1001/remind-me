import { type SQLiteDatabase } from "expo-sqlite";

export default async function initializeDatabase(database: SQLiteDatabase) {
  await database.execAsync(`
    PRAGMA foreign_keys = ON;

    CREATE TABLE IF NOT EXISTS reminders (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      type TEXT NOT NULL,
      title TEXT NOT NULL,
      description TEXT,
      is_active INTEGER NOT NULL DEFAULT 1
    );

    CREATE TABLE IF NOT EXISTS schedules (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      item_id INTEGER NOT NULL,
      date TEXT NOT NULL,
      recurrence_type TEXT NOT NULL,
      interval INTEGER NOT NULL DEFAULT 1 CHECK (interval > 0),
      weekdays TEXT,
      notify INTEGER NOT NULL DEFAULT 1,
      minutes_before INTEGER NOT NULL DEFAULT 0 CHECK (minutes_before >= 0),
      FOREIGN KEY (item_id) REFERENCES reminders(id) ON DELETE CASCADE
    );

    CREATE INDEX IF NOT EXISTS idx_schedules_item_id
      ON schedules(item_id);

    CREATE INDEX IF NOT EXISTS idx_schedules_date
      ON schedules(date);
  `);
}
