import { Input } from "@/components/form/Input";
import { router } from "expo-router";
import { useState } from "react";
import { Button, View } from "react-native";
import Toast from "react-native-toast-message";
import useReminderDatabase from "./database/useReminderDatabase";

export default function Reminderform() {
  const { create } = useReminderDatabase();
  const [type, setType] = useState("");
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [isActive, setIsActive] = useState(1);

  async function handlePress() {
    const result = await create({
      type: type,
      title: title,
      description: description,
    });

    if (result?.insertedRowId) {
      Toast.show({
        type: "success",
        text1: "Sucesso 🎉",
        text2: "Reminder criado com sucesso!",
      });
      router.replace("/");
    } else {
      Toast.show({ type: "error", text1: "Erro ao criar o Reminder!" });
    }
  }

  return (
    <View
      style={{
        flex: 1,
        justifyContent: "center",
        padding: 32,
        gap: 16,
      }}
    >
      <Input placeholder="Titulo" onChangeText={setTitle} value={title} />
      <Input
        placeholder="Descrição"
        onChangeText={setDescription}
        value={description}
      />
      <Button title="Salvar" onPress={handlePress} />
    </View>
  );
}
