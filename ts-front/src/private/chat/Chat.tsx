import React, { useEffect, useState } from "react";
import { Client, over } from "stompjs";
import SockJS from 'sockjs-client';
import ChatContent from "./ChatContent";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "@/store/store";
import { Box } from "@mui/material";
import { ChatPayload, ChatUserData } from "@/models";
import { addMessageAction } from "@/store/conversations/conversation.action";

let stompClient: Client;

const Chat: React.FC = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { selectedConversation } = useSelector((state: RootState) => state.conversations);
  const { user } = useSelector((state: RootState) => state.user);
  const { messages } = useSelector((state: RootState) => state.conversations);

  const [ message, setMessage ] = useState<ChatUserData>({
    senderId: user?.uuid || "",
    conversationId: selectedConversation?.id || "",
    content: "",
    connected: false,
  });

  useEffect(() => {
    connect();
  }, []);

  const connect = () => {
    const sock = new SockJS("http://localhost:8089/ws");
    stompClient = over(sock);
    stompClient.connect({}, onConnect, onError);
  };

  const onConnect = () => {
    setMessage({ ...message, connected: true });
    stompClient.subscribe(`/conversation/${message.conversationId}/private`, onPrivateMessageReceived);
    // userJoin();
  };

  const onError = (error: any) => {
    console.error(error);
  };

  const onPrivateMessageReceived = (payload: { body: string }) => {
    console.log( "Received message: " + payload.body);
    const payloadData: ChatPayload = JSON.parse(payload.body);
    dispatch(addMessageAction(payloadData));
  };



  const sendMessage = () => {
    if (stompClient && stompClient.connected) {
      const chatMessage: ChatPayload = {
        senderId: message.senderId,
        conversationId: selectedConversation?.id || "",
        content: message.content,
        status: "MESSAGE",
      };
      // dispatch(addMessageAction(chatMessage));
      stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
      setMessage({ ...message, content: "" });
    }
  };

  const handleMessageInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setMessage({ ...message, content: value });
  };

  return (
    <Box minWidth="100%" minHeight="100%">
      <ChatContent
        message={message}
        handleMessageInput={handleMessageInput}
        sendMessage={sendMessage}
      />
    </Box>
  );
};

export default Chat;