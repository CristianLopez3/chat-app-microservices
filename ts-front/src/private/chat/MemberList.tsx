import { Button } from "@mui/material";
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
        <Button
          onClick={() => {
            setChatArea("PUBLIC");
          }}
          variant="contained"
          color="primary"
          fullWidth
          className={`member ${chatArea === "PUBLIC" && "active"}`}
        >
          PUBLIC CHAT
        </Button>
        {[...privateMessage.keys()].map((name, index) => (
          <Button
            type="button"
            fullWidth
            variant="contained"
            color="primary"
            sx={{ my: 1 }}
            onClick={() => {
              setChatArea(name);
            }}
            className={`member ${chatArea === name && "active"}`}
            key={index}
          > {name} </Button>
        ))}
      </ul>
    </div>
  );
};

export default MemberList;