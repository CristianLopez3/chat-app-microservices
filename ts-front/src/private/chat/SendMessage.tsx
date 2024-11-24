import { Box, Button, IconButton, TextField } from "@mui/material";
import React from "react";
import SendIcon from '@mui/icons-material/Send';
import { ChatUserData } from "@/models";

type SendMessageProps = {
  userData: ChatUserData;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const SendMessage: React.FC<SendMessageProps> = ({
  userData,
  handleMessageInput,
  sendPublicMessage,
  sendPrivateMessage,
}) => {
  const { recievername } = userData;
  return (
    <Box
      sx={{ display: "flex", justifyContent: "space-between", alignItems: "center", gap: "15px" }}
    >
      <TextField
        fullWidth
        multiline
        id="message"
        type="text"
        className="input-message"
        placeholder="Enter the message"
        value={userData.message}
        onChange={handleMessageInput}
        variant="outlined"
        sx={{ fontSize: "12px" }}
      />
      <IconButton
        aria-label="send"
        type="button"
        onClick={ recievername === "PUBLIC" ? sendPublicMessage : sendPrivateMessage}
      >
        <SendIcon color="primary" fontSize="large" />
      </IconButton>
    </Box>
  );
};

export default SendMessage;