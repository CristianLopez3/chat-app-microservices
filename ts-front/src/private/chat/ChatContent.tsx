import React from "react";
import SendMessage from "./SendMessage";
import { Box, List, ListItem, ListItemText } from "@mui/material";
import Grid from '@mui/material/Grid2';
import { ChatUserData, ChatPayload } from "@/models";
import { StringAvatar } from "@/components/avatar";

type ChatContentProps = {
  privateMessage: Map<string, ChatPayload[]>;
  publicMessage: ChatPayload[];
  userData: ChatUserData;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const ChatContent: React.FC<ChatContentProps> = ({
  privateMessage,
  publicMessage,
  userData,
  handleMessageInput,
  sendPublicMessage,
  sendPrivateMessage,
}) => {
  return (
    <Box sx={{ minWidth: "100%", height: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Grid container direction="column" sx={{ flex: 1, overflow: 'hidden' }}>
        <Grid sx={{ flex: 1, overflowY: 'scroll', background: "gray", border: "1px solid #000" }} className="chat-scrollbar">
          <List sx={{ height: "95%" }}>
            {userData.receiverId === "PUBLIC"
              ? publicMessage.map((chat, index) => (
                <ListItem
                  sx={{
                    display: 'flex',
                    justifyContent: chat.senderId === userData.senderId ? 'flex-end' : 'flex-start',
                    alignItems: 'flex-start',
                  }}
                  key={index}
                >
                  <ListItemText
                    color='#7678ed'
                    primary={chat.message}
                    sx={{
                      paddingLeft: chat.senderId !== userData.senderId ? "5px" : undefined,
                      paddingRight: chat.senderId === userData.senderId ? "5px" : undefined,
                      textAlign: chat.senderId === userData.senderId ? 'right' : 'left',
                      background: "red",
                      maxWidth: '70%',
                      wordBreak: 'break-word',
                      overflowWrap: 'anywhere',
                    }}
                  />
                </ListItem>
              ))
              : privateMessage.get(userData.receiverId)?.map((chat, index) => (
                <ListItem
                  sx={{
                    display: 'flex',
                    justifyContent: chat.senderId === userData.senderId ? 'flex-end' : 'flex-start',
                    alignItems: 'flex-start',
                  }}
                  key={index}
                >
                  <ListItemText
                    color='#7678ed'
                    primary={chat.message}
                    sx={{
                      paddingLeft: chat.senderId !== userData.senderId ? "5px" : undefined,
                      paddingRight: chat.senderId === userData.senderId ? "5px" : undefined,
                      textAlign: chat.senderId === userData.senderId ? 'right' : 'left',
                      background: "red",
                      maxWidth: '70%',
                      wordBreak: 'break-word',
                      overflowWrap: 'anywhere',
                    }}
                  />
                </ListItem>
              ))}
          </List>
        </Grid>
        <Grid>
          <Box sx={{ borderTop: '1px solid #ccc', padding: 2 }}>
            <SendMessage
              userData={userData}
              handleMessageInput={handleMessageInput}
              sendPublicMessage={sendPublicMessage}
              sendPrivateMessage={sendPrivateMessage}
            />
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default ChatContent;