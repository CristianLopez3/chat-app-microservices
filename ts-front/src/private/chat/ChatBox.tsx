import React from "react";
import MemberList from "./MemberList";
import ChatContent from "./ChatContent";

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
    <div className="chat-box">
      <MemberList
        chatArea={chatArea}
        setChatArea={setChatArea}
        privateMessage={privateMessage}
      />
      <ChatContent
        chatArea={chatArea}
        privateMessage={privateMessage}
        publicMessage={publicMessage}
        userData={userData}
        handleMessageInput={handleMessageInput}
        sendPublicMessage={sendPublicMessage}
        sendPrivateMessage={sendPrivateMessage}
      />
    </div>
  );
};

export default ChatBox;