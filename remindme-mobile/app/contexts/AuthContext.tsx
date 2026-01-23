import { GoogleSignin } from "@react-native-google-signin/google-signin";
import React, { createContext, useContext, useState, ReactNode } from "react";
import Toast from "react-native-toast-message";

export type User = {
  name: string;
  email: string;
  accessToken: string;
};

type AuthContextData = {
  user: User | null;
  loading: boolean;
  signInWithGoogle: () => Promise<void>;
  signOut: () => void;
};

const AuthContext = createContext<AuthContextData>({} as AuthContextData);

type AuthProviderProps = {
  children: ReactNode;
};

export function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(false);

  async function signInWithGoogle() {
    try {
      console.log("Tentando logar");
      setLoading(true);

      await GoogleSignin.hasPlayServices();
      const googleUser = await GoogleSignin.signIn();

      console.log(googleUser);
    } catch (error) {
      console.log(error);

      Toast.show({
        type: "error",
        text1: "Erro ao fazer login",
      });
    } finally {
      setLoading(false);
    }
  }

  function signOut() {
    setUser(null);
    GoogleSignin.signOut();
  }

  return (
    <AuthContext.Provider
      value={{
        user,
        loading,
        signInWithGoogle,
        signOut,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
