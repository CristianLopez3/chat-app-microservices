import React, { useState } from 'react';
import { Container, Box, Typography } from '@mui/material';
import { useSelector } from 'react-redux';
import { RootState } from '@/store/store';
import { ChatList } from '@/components/List/ChatList';
import { AppRoutes, ChatMember } from '@/models';
import { Link } from "react-router-dom";
import Chat from '@/private/chat/Chat';
import MemberList from '@/private/chat/MemberList';

const Dashboard: React.FC = () => {
  const user = useSelector((state: RootState) => state.user.user);
  const [chatArea, setChatArea] = useState<string>('PUBLIC');
  const [selectedChat, setSelectedChat] = useState<ChatMember | null>(null);

  const [chats, setChats] = useState<ChatMember[]>([
    { id: '1', name: 'Daniel Torres', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '2', name: 'Cristian Lopez', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '3', name: 'Sergio Mora', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '4', name: 'Karen Ruiz', lastMessage: 'Hi there! I\'m using this chat', selected: false },
  ]);

  const handleChatSelect = (member: ChatMember) => {
    const selectedChatItem = chats.find(chat => chat.id === member.id)
    if (selectedChatItem) {
      setSelectedChat(selectedChatItem);
    }
  };

  return (
    <Container maxWidth="xl">
      <Box
        display="flex"
        alignItems="center"
        justifyContent="space-between"
        minHeight="99vh"
      >
        <Box minWidth="30%" minHeight="100vh">
          <MemberList
            chatArea={chatArea}
            members={chats}
            onChatSelect={handleChatSelect}
            setChatArea={setChatArea}
            setChats={setChats}
          />
        </Box>

        <Box
          minWidth="70%"
          minHeight="100vh"
          display="flex"
          justifyContent="center"
          alignItems="center"
        >
          {
            selectedChat?.name ? <>
              <Typography variant="h4" component="h1" align="center">
                {selectedChat.name}
              </Typography>
              <Box
                minWidth="70%"
                minHeight={{ sm: '100vh' }}
                display="flex"
                justifyContent="center"
                alignItems="center"
                sx={{ border: '1px solid red', padding: '1rem' }}
              >
                <Chat />
              </Box>
            </> :
              <Typography variant="h4" component="h1" align="center">
                <Link to={AppRoutes.private.chat}>chat</Link>
              </Typography>
          }</Box>
      </Box>
    </Container>
  );
};

export default Dashboard;