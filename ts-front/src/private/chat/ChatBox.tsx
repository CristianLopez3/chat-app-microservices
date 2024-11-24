import React from "react";
import ChatContent from "./ChatContent";
import { Box } from "@mui/material";
import { ChatPayload, ChatUserData } from "@/models";

type ChatBoxProps = {
  privateMessage: Map<string, any[]>;
  publicMessage: ChatPayload[];
  userData: ChatUserData;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const ChatBox: React.FC<ChatBoxProps> = ({
  privateMessage,
  publicMessage,
  userData,
  handleMessageInput,
  sendPublicMessage,
  sendPrivateMessage,
}) => {
  return (
    <Box
      sx={{
        minHeight: "100vh",
        component: "section",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minWidth: "100%",
      }}
    >
      <ChatContent
        privateMessage={privateMessage}
        publicMessage={publicMessage}
        userData={userData}
        handleMessageInput={handleMessageInput}
        sendPublicMessage={sendPublicMessage}
        sendPrivateMessage={sendPrivateMessage}
      />
    </Box>
  );
};

export default ChatBox;