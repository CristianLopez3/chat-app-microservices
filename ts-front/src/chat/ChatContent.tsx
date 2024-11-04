import React from "react";
import SendMessage from "./SendMessage";

type ChatContentProps = {
  chatArea: string;
  privateMessage: Map<string, any[]>;
  publicMessage: any[];
  userData: any;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const ChatContent: React.FC<ChatContentProps> = ({
  chatArea,
  privateMessage,
  publicMessage,
  userData,
  handleMessageInput,
  sendPublicMessage,
  sendPrivateMessage,
}) => {
  return (
    <div className="chat-content">
      <ul className="chat-messages">
        {chatArea === "PUBLIC"
          ? publicMessage.map((chat, index) => (
              <li
                className={`message ${chat.senderName === userData.username && "self"}`}
                key={index + chat.receiverName}
              >
                {chat.senderName !== userData.username && (
                  <div className="avatar">{chat.senderName}</div>
                )}
                <div className="message-data">{chat.message}</div>
                {chat.senderName === userData.username && (
                  <div className="avatar self">{chat.senderName}</div>
                )}
              </li>
            ))
          : privateMessage.get(chatArea)?.map((chat, index) => (
              <li
                className={`message ${chat.senderName === userData.username && "self"}`}
                key={index}
              >
                {chat.senderName !== userData.username && (
                  <div className="avatar">{chat.senderName}</div>
                )}
                <div className="message-data">{chat.message}</div>
                {chat.senderName === userData.username && (
                  <div className="avatar self">{chat.senderName}</div>
                )}
              </li>
            ))}
      </ul>
      <SendMessage
        chatArea={chatArea}
        userData={userData}
        handleMessageInput={handleMessageInput}
        sendPublicMessage={sendPublicMessage}
        sendPrivateMessage={sendPrivateMessage}
      />
    </div>
  );
};

export default ChatContent;