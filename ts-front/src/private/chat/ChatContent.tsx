import React from "react";
import SendMessage from "./SendMessage";
import { Box, List, ListItem, ListItemText } from "@mui/material";
import Grid from '@mui/material/Grid2';
import { ChatUserData } from "@/models";
import { StringAvatar } from "@/components/avatar";

type ChatContentProps = {
  privateMessage: Map<string, any[]>;
  publicMessage: any[];
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
            {userData.username === "PUBLIC"
              ? publicMessage.map((chat, index) => (
                <li
                  className={`message ${chat.senderName === userData.username ? "self" : ""}`}
                  key={index + chat.receiverName}
                >
                  {chat.senderName !== userData.username && (
                    <div className="avatar">{chat.senderName + "Chat"} </div>
                  )}
                  <div className="message-data">{chat.message}</div>
                  {chat.senderName === userData.username && (
                    <>
                      <div className="avatar self">{chat.senderName}</div>
                    </>
                  )}
                </li>
              ))
              : privateMessage.get(userData.recievername)?.map((chat, index) => (
                <ListItem
                  sx={{
                    display: 'flex',
                    justifyContent: chat.senderName === userData.username ? 'flex-end' : 'flex-start',
                    alignItems: 'flex-start', // Alinea el contenido en la parte superior.
                  }}
                  key={index}
                >
                  {chat.senderName !== userData.username && (
                    <StringAvatar name={chat.senderName} />
                  )}

                  <ListItemText
                    color='#7678ed'
                    primary={chat.message}
                    sx={{
                      paddingLeft: chat.senderName !== userData.username ? "5px" : undefined,
                      paddingRight: chat.senderName === userData.username ? "5px" : undefined,
                      textAlign: chat.senderName === userData.username ? 'right' : 'left',
                      background: "red",
                      maxWidth: '70%',
                      wordBreak: 'break-word',
                      overflowWrap: 'anywhere',
                    }}
                  />

                  {chat.senderName === userData.username && (
                    <StringAvatar name={chat.senderName} />
                  )}
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