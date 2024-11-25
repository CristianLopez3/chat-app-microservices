import React, { useEffect, useState } from 'react';
import { Container, Box, Typography } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '@/store/store';
import { AppRoutes, ChatUserData, UserResponse } from '@/models';
import { Link } from "react-router-dom";
import Chat from '@/private/chat/Chat';
import MemberList from '@/private/chat/MemberList';
import { getUserConversationsAction } from '@/store/users';

const Dashboard: React.FC = () => {
  const { user, conversations } = useSelector((state: RootState) => state.user);
  const dispatch = useDispatch<AppDispatch>();
  const [selectedChat, setSelectedChat] = useState<UserResponse | null>(null);
  const [userData, setUserData] = useState<ChatUserData>({
    username: user?.username || "",
    recievername: "PUBLIC",
    message: "",
    connected: false,
  });

  const fetchConversations = () => {
    if (user) {
      dispatch(getUserConversationsAction(user.userId));
    }
  };

  useEffect(() => {
    if (user) {
      fetchConversations();
    }
  }, []);

  const handleChatSelect = (member: UserResponse) => {
    const selectedChatItem = conversations.find(chat => chat.userId === member.userId);

    if (selectedChatItem) {
      setUserData(prevState => ({
        ...prevState,
        recievername: selectedChatItem.username,
      }));
      setSelectedChat(selectedChatItem);
      console.info("Selected chat");
      console.log( selectedChatItem)
    }
  };

  return (
    <Container maxWidth="xl">
      <Box display="flex" alignItems="center" justifyContent="space-between" minHeight="99vh">
        <Box minWidth="30%" minHeight="100vh">
          <MemberList
            selected={selectedChat}
            members={conversations}
            onChatSelect={handleChatSelect}
            setChats={fetchConversations}
          />
        </Box>
        <Box
          minWidth="70%"
          minHeight="100vh"
          display="flex"
          justifyContent="center"
          alignItems="center"
        >
          {selectedChat ? (
            <Box minWidth="100%" minHeight={{ sm: '99vh' }}>
              <Chat userData={userData} setUserData={setUserData} selected={selectedChat} />
            </Box>
          ) : (
            <Typography variant="h4" component="h1" align="center">
              <Link to={AppRoutes.private.chat}>chat</Link>
            </Typography>
          )}
        </Box>
      </Box>
    </Container>
  );
};

export default Dashboard;