import { RootState } from "@/store/store";
import { Box, Container, Typography } from "@mui/material";
import { useSelector } from "react-redux";
import { ChatList } from "@/components/List/ChatList";


const Dashboard: React.FC = () => {
  const user = useSelector((state: RootState) => state.user.user);
  const chats = [
    { id: '1', name: 'Daniel Torres', lastMessage: 'Hi there! i\'m using this chat' },
    { id: '2', name: 'Cristian Lopez', lastMessage: 'Hi there! i\'m using this chat' },
    { id: '3', name: 'Sergio Mora', lastMessage: 'Hi there! i\'m using this chat' },
    { id: '4', name: 'Karen Ruiz', lastMessage: 'Hi there! i\'m using this chat' },
  ];

  return (
    <Container maxWidth="xl"  >
      <Box
        display="flex"
        alignItems="center"
        justifyContent="space-between"
        minHeight="99vh"
      >
        <Box minWidth={"30%"} minHeight="100vh">
          <ChatList items={chats} />
        </Box>

        <Box
          minWidth="70%"
          minHeight="100vh"
          display="flex"
          justifyContent="center"
          alignItems="center"
        >
          <Typography variant="h4" component="h1" align="center">
            Dashboard, {user.name} go and chat!
          </Typography>
        </Box>

      </Box>
    </Container>
  );
};

export default Dashboard;