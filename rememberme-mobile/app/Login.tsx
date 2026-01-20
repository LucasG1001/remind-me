import React from "react";
import { StyleSheet, Text, View, TouchableOpacity } from "react-native";
import { Fontisto } from "@expo/vector-icons";
import { useAuth } from "./contexts/AuthContext";

export default function Login() {
  const { signInWithGoogle, loading } = useAuth();
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Bem-vindo de volta</Text>
      <Text style={styles.subtitle}>Acesse sua conta para continuar</Text>

      <TouchableOpacity
        onPress={signInWithGoogle}
        style={styles.googleButton}
        activeOpacity={0.8}
        disabled={loading}
      >
        <Fontisto name="google" size={20} color="#FFF" style={styles.icon} />
        <Text style={styles.buttonText}>Entrar com Google</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#121212",
    justifyContent: "center",
    padding: 24,
  },
  title: {
    fontSize: 28,
    fontWeight: "bold",
    color: "#FFFFFF",
    textAlign: "center",
    marginBottom: 8,
  },
  subtitle: {
    fontSize: 16,
    color: "#A0A0A0",
    textAlign: "center",
    marginBottom: 40,
  },
  googleButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#4285F4",
    height: 56,
    borderRadius: 12,
    // Sombra para Android
    elevation: 3,
  },
  icon: {
    marginRight: 12,
  },
  buttonText: {
    color: "#FFFFFF",
    fontSize: 16,
    fontWeight: "600",
  },
});
