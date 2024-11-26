import React, { useEffect, useState } from "react";
import { Client, over } from "stompjs";
import SockJS from 'sockjs-client';
import ChatContent from "./ChatContent";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "@/store/store";
import { addMessage } from "@/store/messages/message.actions";
import { Box } from "@mui/material";
import { ChatMember, ChatPayload, ChatUserData, UserResponse } from "@/models";

let stompClient: Client;

type ChatProps = {
  userData: ChatUserData;
  setUserData: (userData: ChatUserData) => void;
  selected?: UserResponse;
}

const Chat: React.FC<ChatProps> = ({ selected, userData, setUserData }) => {
  const user = useSelector((state: RootState) => state.user.user);
  const messages = useSelector((state: RootState) => state.messages.messages);
  const [privateMessage, setPrivateMessage] = useState<Map<string, ChatPayload[]>>(new Map());
  const [publicMessage, setPublicMessage] = useState<ChatPayload[]>([]);

  const registerUser = () => {
    connect();
  };

  const connect = () => {
    let sock = new SockJS("http://localhost:8089/ws");
    stompClient = over(sock);
    stompClient.connect({}, onConnect, onError);
  };

  const onConnect = () => {
    setUserData({ ...userData, connected: true });
    stompClient.subscribe("/chatroom/public", (payload: { body: string }) => {
      onPublicMessageReceived(payload);
    });
    stompClient.subscribe("/user/" + userData.senderId + "/private", onPrivateMessageReceived);
    userJoin();
  };

  const onError = (error: any) => {
    console.log(error);
  };

  const onPublicMessageReceived = (payload: { body: string }) => {
    let payloadData: ChatPayload = JSON.parse(payload.body);
    switch (payloadData.status) {
      case "JOIN":
        if (!privateMessage.get(payloadData.senderId)) {
          privateMessage.set(payloadData.senderId, []);
          setPrivateMessage(new Map(privateMessage));
        }
        break;
      case "MESSAGE":
        publicMessage.push(payloadData);
        setPublicMessage([...publicMessage]);
        break;
    }
  };

  const onPrivateMessageReceived = (payload: { body: string }) => {
    var payloadData: ChatPayload = JSON.parse(payload.body);
    if (!privateMessage.get(payloadData.senderId)) {
      privateMessage.set(payloadData.senderId, []);
    }
    privateMessage.get(payloadData.senderId)?.push(payloadData);
    setPrivateMessage(new Map(privateMessage));
  };

  const userJoin = () => {
    var chatMessage = {
      senderId: userData.senderId,
      status: "JOIN",
    };
    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };

  const sendPublicMessage = () => {
    if (stompClient) {
      let chatMessage = {
        senderId: userData.senderId,
        message: userData.message,
        status: "MESSAGE",
      };
      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };

  const sendPrivateMessage = () => {
    if (stompClient) {
      let chatMessage: ChatPayload = {
        senderId: userData.senderId,
        receiverId: userData.receiverId,
        message: userData.message,
        status: "MESSAGE",
      };
      if (!privateMessage.get(userData.receiverId)) {
        privateMessage.set(userData.receiverId, []);
      }
      privateMessage.get(userData.receiverId)?.push(chatMessage);
      setPrivateMessage(new Map(privateMessage));
      stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };

  const handleMessageInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setUserData({ ...userData, message: value });
  };

  useEffect(() => {
    connect();
  }, []);

  return (
    <Box minWidth="100%" minHeight="100%">
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

export default Chat;