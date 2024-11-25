import React from "react";
import { Button, List, ListItemAvatar, Typography } from "@mui/material";
import { ChatButton } from "@/components/Button";
import { ChatMember, UserResponse } from "@/models";
import { StringAvatar } from "@/components/avatar";

type MemberListProps = {
  members: UserResponse[];
  selected: UserResponse | null;
  onChatSelect: (member: UserResponse) => void;
  setChats: (chats: UserResponse[]) => void;
};

const MemberList: React.FC<MemberListProps> = ({
  members,
  selected,
  onChatSelect,
  setChats
}) => {

  const handleChatSelect = (member: UserResponse) => {
    const updatedChats = members.map(chat => ({
      ...chat,
      selected: chat.userId === member.userId,
    }));
    onChatSelect(member);
    setChats(updatedChats);
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
      {members.map((member: UserResponse) => (
        <ChatButton
          member={member}
          selected={member.userId === selected?.userId}
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
