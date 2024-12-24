import React, { useEffect, useState } from 'react';
import { Container, Box, Typography } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '@/store/store';
import { AppRoutes, ChatUserData } from '@/models';
import { Link } from "react-router-dom";
import Chat from '@/private/chat/Chat';
import MemberList from '@/private/chat/MemberList';
import { getUserConversationsAction } from '@/store/conversations/conversation.action';

const Dashboard: React.FC = () => {
  const { user } = useSelector((state: RootState) => state.user);
  const { conversations } = useSelector((state: RootState) => state.conversations);
  const { selectedConversation } = useSelector((state: RootState) => state.conversations);
  const dispatch = useDispatch<AppDispatch>();
  const [userData, setUserData] = useState<ChatUserData>({
    senderId: user?.uuid || "",
    conversationId: selectedConversation?.id || "",
    message: "",
    connected: false,
  });

  const fetchConversations = () => {
    if (user) {
      dispatch(getUserConversationsAction(user.uuid!));
    }
  };

  useEffect(() => {
    if (user) {
      fetchConversations();
    }
  }, []);

  return (
    <Container maxWidth="xl">
      <Box display="flex" alignItems="center" justifyContent="space-between" minHeight="99vh">
        <Box minWidth="30%" minHeight="100vh">
          <MemberList members={conversations} />
        </Box>
        <Box
          minWidth="70%"
          minHeight="100vh"
          display="flex"
          justifyContent="center"
          alignItems="center"
        >
          {selectedConversation ? (
             <Box minWidth="100%" minHeight={{ sm: '99vh' }}>
              <Chat userData={userData} setUserData={setUserData} />
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