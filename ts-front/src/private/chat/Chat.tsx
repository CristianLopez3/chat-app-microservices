import React, { useEffect, useState } from "react";
import { Client, over } from "stompjs";
import SockJS from 'sockjs-client';
import ChatBox from "./ChatBox";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "@/store/store";
import { addMessage } from "@/store/messages/message.actions";
import { Box } from "@mui/material";


let stompClient: Client;

type Payload = {
  senderName: string;
  receiverName: string;
  message: string;
  status: string;
}

const Chat: React.FC = () => {
  const user = useSelector((state: RootState) => state.user.user);
  const messages = useSelector((state: RootState) => state.messages.messages);
  const dispatch = useDispatch<AppDispatch>();

  const [privateMessage, setPrivateMessage] = useState<Map<string, Payload[]>>(new Map());
  const [publicMessage, setPublicMessage] = useState<Payload[]>([]);
  const [chatArea, setChatArea] = useState<string>("PUBLIC");
  const [userData, setUserData] = useState({
    username: user.username,
    recievername: "",
    message: "",
    connected: false,
  });

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
      let count = 0;
      console.log("Data received: ", payload.body);
      console.info('Message number: ' + count++);
      console.log("Length of publicMessage: ", publicMessage.length)
      onPublicMessageReceived(payload);
    });
    stompClient.subscribe("/user/" + userData.username + "/private", onPrivateMessageReceived);
    userJoin();
  };

  const onError = (error: any) => {
    console.log(error);
  };

  const onPublicMessageReceived = (payload: { body: string }) => {
    let payloadData: Payload = JSON.parse(payload.body);
    switch (payloadData.status) {
      case "JOIN":
        if (!privateMessage.get(payloadData.senderName)) {
          privateMessage.set(payloadData.senderName, []);
          setPrivateMessage(new Map(privateMessage));
        }
        break;
      case "MESSAGE":
        // console.table(publicMessage);
        publicMessage.push(payloadData);
        addMessage(payloadData);
        setPublicMessage([...publicMessage]);
        break;
    }
  };

  const onPrivateMessageReceived = (payload: { body: string }) => {
    var payloadData = JSON.parse(payload.body);
    if (!privateMessage.get(payloadData.senderName)) {
      privateMessage.set(payloadData.senderName, []);
    }
    privateMessage.get(payloadData.senderName)?.push(payloadData);
    setPrivateMessage(new Map(privateMessage));
  };

  const userJoin = () => {
    var chatMessage = {
      senderName: userData.username,
      status: "JOIN",
    };
    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };

  const sendPublicMessage = () => {
    if (stompClient) {
      let chatMessage = {
        senderName: userData.username,
        message: userData.message,
        status: "MESSAGE",
      };
      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };

  const sendPrivateMessage = () => {
    if (stompClient) {
      let chatMessage = {
        senderName: userData.username,
        receiverName: chatArea,
        message: userData.message,
        status: "MESSAGE",
      };
      if (!privateMessage.get(chatArea)) {
        privateMessage.set(chatArea, []);
      }
      privateMessage.get(chatArea)?.push(chatMessage);
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
  }, [])

  return (
    <Box
      minWidth="70%"
      minHeight="100%"
      sx={{border: "1px solid black"}}
    >
      <ChatBox
        chatArea={chatArea}
        setChatArea={setChatArea}
        privateMessage={privateMessage}
        publicMessage={messages}
        userData={userData}
        handleMessageInput={handleMessageInput}
        sendPublicMessage={sendPublicMessage}
        sendPrivateMessage={sendPrivateMessage}
      />
    </Box>
  );
};

export default Chat;