import React from "react";
import SendMessage from "./SendMessage";
import { Box, List, ListItem, ListItemText } from "@mui/material";
import Grid from '@mui/material/Grid2';
import { ChatUserData, ChatPayload } from "@/models";

type ChatContentProps = {
  messages: Record<string, ChatPayload[]>;
  message: ChatUserData;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendMessage: () => void;
};

const ChatContent: React.FC<ChatContentProps> = ({
  messages,
  message,
  handleMessageInput,
  sendMessage,
}) => {
  const conversationMessages = messages[message.conversationId] || [];

  return (
    <Box sx={{ minWidth: "100%", height: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Grid container direction="column" sx={{ flex: 1, overflow: 'hidden' }}>
        <Grid sx={{ flex: 1, overflowY: 'scroll', background: "gray", border: "1px solid #000" }} className="chat-scrollbar">
          <List sx={{ height: "95%" }}>
            {conversationMessages.map((chat, index) => (
              <ListItem
                sx={{
                  display: 'flex',
                  justifyContent: chat.senderId === message.senderId ? 'flex-end' : 'flex-start',
                  alignItems: 'flex-start',
                }}
                key={index}
              >
                <ListItemText
                  primary={chat.content}
                  sx={{
                    paddingLeft: chat.senderId !== message.senderId ? "5px" : undefined,
                    paddingRight: chat.senderId === message.senderId ? "5px" : undefined,
                    textAlign: chat.senderId === message.senderId ? 'right' : 'left',
                    background: chat.senderId === message.senderId ? "#e0f7fa" : "#f1f8e9",
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
              message={message}
              handleMessageInput={handleMessageInput}
              sendMessage={sendMessage}
            />
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default ChatContent;