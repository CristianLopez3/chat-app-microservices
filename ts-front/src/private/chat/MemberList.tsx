import React from "react";
import { Button, List, ListItemAvatar, Typography } from "@mui/material";
import { ChatButton } from "@/components/Button";
import { ChatMember } from "@/models";
import { StringAvatar } from "@/components/avatar";

type MemberListProps = {
  members: ChatMember[];
  onChatSelect: (member: ChatMember) => void;
  setChats: (chats: ChatMember[]) => void;
};

const MemberList: React.FC<MemberListProps> = ({
  members,
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
        variant="contained"
        color="primary"
        sx={{borderRadius: "13px"}}
      >
        <ListItemAvatar>
          <StringAvatar name="PUBLIC CHAT" />
        </ListItemAvatar>
        PUBLIC CHAT
      </Button>
      {members.map((member: ChatMember) => (
        <ChatButton
          member={member}
          onChatSelect={handleChatSelect}
        />
      ))}
      <Typography variant="h6" sx={{ ml: 1, mt: 2 }}>
        {members.filter(member => member.selected).map(member => member.name)}
      </Typography>
    </List>
  );
};

export default MemberList;
