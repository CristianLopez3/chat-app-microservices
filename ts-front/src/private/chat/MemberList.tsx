import React from "react";
import { Button, List } from "@mui/material";
import { ChatButton } from "@/components/Button";
import { ChatMember } from "@/models";

type MemberListProps = {
  members: ChatMember[];
  chatArea: string;
  setChatArea: React.Dispatch<React.SetStateAction<string>>;
  onChatSelect: (member: ChatMember) => void;
  setChats: (chats: ChatMember[]) => void;
};

const MemberList: React.FC<MemberListProps> = ({
  members,
  chatArea,
  setChatArea,
  onChatSelect,
  setChats
}) => {

  const handleChatSelect = (member: ChatMember) => {
    const updatedChats = members.map(chat => ({
      ...chat,
      selected: chat.id === member.id,
    }));
    setChats(updatedChats);
    onChatSelect(member);
  };

  return (
    <List sx={{ width: '90%', ml: 1 }}>
      <Button
        fullWidth
        onClick={() => setChatArea("PUBLIC")}
        variant="contained"
        color="primary"
        className={`member ${chatArea === "PUBLIC" && "active"}`}
      >
        PUBLIC CHAT
      </Button>
      {members.map((member: ChatMember) => (
        <ChatButton
          member={member}
          onChatSelect={handleChatSelect}
        />
      ))}
    </List>
  );
};

export default MemberList;
