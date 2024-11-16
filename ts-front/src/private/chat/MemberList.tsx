import React from "react";

type MemberListProps = {
  chatArea: string;
  setChatArea: React.Dispatch<React.SetStateAction<string>>;
  privateMessage: Map<string, any[]>;
};

const MemberList: React.FC<MemberListProps> = ({ chatArea, setChatArea, privateMessage }) => {
  return (
    <div className="member-list">
      <ul>
        <li
          onClick={() => {
            setChatArea("PUBLIC");
          }}
          className={`member ${chatArea === "PUBLIC" && "active"}`}
        >
          PUBLIC CHAT
        </li>
        {[...privateMessage.keys()].map((name, index) => (
          <li
            onClick={() => {
              setChatArea(name);
            }}
            className={`member ${chatArea === name && "active"}`}
            key={index}
          >
            {name}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default MemberList;