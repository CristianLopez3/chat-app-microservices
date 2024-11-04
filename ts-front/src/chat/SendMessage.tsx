import React from "react";

type SendMessageProps = {
  chatArea: string;
  userData: any;
  handleMessageInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  sendPublicMessage: () => void;
  sendPrivateMessage: () => void;
};

const SendMessage: React.FC<SendMessageProps> = ({
  chatArea,
  userData,
  handleMessageInput,
  sendPublicMessage,
  sendPrivateMessage,
}) => {
  return (
    <div className="send-message">
      <input
        type="text"
        className="input-message"
        placeholder="Enter the message"
        value={userData.message}
        onChange={handleMessageInput}
      />
      <button
        type="button"
        className="send-button"
        onClick={chatArea === "PUBLIC" ? sendPublicMessage : sendPrivateMessage}
      >
        send
      </button>
    </div>
  );
};

export default SendMessage;