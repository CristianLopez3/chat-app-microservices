import React from "react";
import MemberList from "./MemberList";
import ChatContent from "./ChatContent";
import { Box } from "@mui/material";

type ChatBoxProps = {
  chatArea: string;
  setChatArea: React.Dispatch<React.SetStateAction<string>>;
  privateMessage: Map<string, any[]>;
  publicMessage: any[];
  userData: any;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const ChatBox: React.FC<ChatBoxProps> = ({
  chatArea,
  setChatArea,
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
        alignItems: "center"
      }}
    >
      <ChatContent
        chatArea={chatArea}
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