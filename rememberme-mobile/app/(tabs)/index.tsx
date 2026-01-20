import { ReactNode } from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext";
import Login from "../Login";
import Reminder from "../Reminder";
import { View, ActivityIndicator } from "react-native";

function ProtectedRoute({ children }: { children: ReactNode }) {
  const { user, loading } = useAuth();

  if (loading) {
    return <ActivityIndicator size="large" />;
  }

  if (!user) {
    return <Login />;
  }

  return children;
}

export default function HomeScreen() {
  return (
    <>
      <AuthProvider>
        <ProtectedRoute>
          <Reminder />
        </ProtectedRoute>
      </AuthProvider>
    </>
  );
}
