import React, { useState } from 'react';
import { List } from '@mui/material';
import { ChatButton } from '../Button';
import { ChatMember } from '@/models';


type ChatListProps = {
  members: ChatMember[];
  onChatSelect: (member: ChatMember) => void;
};

export const ChatList: React.FC<ChatListProps> = ({ members, onChatSelect }) => {
  const [selectedChatId, setSelectedChatId] = useState<string | null>(null);

  const handleChatClick = (member: ChatMember) => {
    setSelectedChatId(member.id);
    onChatSelect(member);
  };

  return (
    <List sx={{ width: '90%', ml: 1 }}>
      {members.map((member) => (
        <ChatButton
          member={member}
          onChatSelect={handleChatClick}
        />
      ))}
    </List>
  );
};