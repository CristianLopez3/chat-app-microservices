import { ChatItem } from "@/models"
import { List } from "@mui/material"
import { ChatButton } from "../Button"
import { useState } from "react"

type ChatListProps ={
  items: ChatItem[]
}

export const ChatList: React.FC<ChatListProps> = ({ items }) => {
  const [selectedChatId, setSelectedChatId] = useState<string | null>(null);

  const handleChatClick = (id: string) => {
    setSelectedChatId(id);
  };

  return (
    <List sx={{ width: '90%', ml: 1 }}>
      {items.map((item) => (
        <ChatButton
          key={item.id}
          id={item.id}
          name={item.name}
          lastMessage={item.lastMessage}
          selected={item.id === selectedChatId}
          onClick={handleChatClick}
        />
      ))}
    </List>
  );
};