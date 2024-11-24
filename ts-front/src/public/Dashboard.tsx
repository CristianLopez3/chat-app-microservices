import React, { useState } from 'react';
import { Container, Box, Typography } from '@mui/material';
import { useSelector } from 'react-redux';
import { RootState } from '@/store/store';
import { ChatList } from '@/components/List/ChatList';
import { AppRoutes, ChatMember, ChatUserData } from '@/models';
import { Link } from "react-router-dom";
import Chat from '@/private/chat/Chat';
import MemberList from '@/private/chat/MemberList';

const Dashboard: React.FC = () => {
  const user = useSelector((state: RootState) => state.user.user);
  const [selectedChat, setSelectedChat] = useState<ChatMember | null>(null);
  const [userData, setUserData] = useState<ChatUserData>({
    username: user.username,
    recievername: selectedChat?.name || "PUBLIC",
    message: "",
    connected: false,
  });


  const [chats, setChats] = useState<ChatMember[]>([
    { id: '0', name: 'public', lastMessage: 'I am the public message', selected: false },
    { id: '1', name: 'cristian.c.lopez.m@hotmail.com', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '10', name: 'cristian2002', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '2', name: 'Cristian Lopez', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '3', name: 'Sergio Mora', lastMessage: 'Hi there! I\'m using this chat', selected: false },
    { id: '4', name: 'Karen Ruiz', lastMessage: 'Hi there! I\'m using this chat', selected: false },
  ]);

  const handleChatSelect = (member: ChatMember) => {
    const selectedChatItem = chats.find(chat => chat.id === member.id)
    if (selectedChatItem) {
      setUserData({ ...userData, recievername: selectedChatItem.name });
      setSelectedChat(selectedChatItem);
    }
  };

  return (
    <Container maxWidth="xl">
      <Box display="flex" alignItems="center" justifyContent="space-between" minHeight="99vh">

        <Box minWidth="30%" minHeight="100vh">
          <MemberList
            members={chats}
            onChatSelect={handleChatSelect}
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
            selectedChat ?
              <Box minWidth="100%" minHeight={{ sm: '99vh' }} >
                <Chat userData={userData} setUserData={setUserData} selected={selectedChat} />
              </Box>
              :
              <Typography variant="h4" component="h1" align="center">
                <Link to={AppRoutes.private.chat}>chat</Link>
              </Typography>
          }</Box>
      </Box>
    </Container>
  );
};

export default Dashboard;