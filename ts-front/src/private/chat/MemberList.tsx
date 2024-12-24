import React from "react";
import { Button, List, Typography } from "@mui/material";
import { ChatButton } from "@/components/Button";
import { Conversation } from "@/models";
import { useSelector } from "react-redux";
import { RootState } from "@/store";

type MemberListProps = {
  members: Conversation[];
};

const MemberList: React.FC<MemberListProps> = ({
  members
}) => {
  const { user } = useSelector((state: RootState) => state.user);

  return (
    <List sx={{ width: '90%', ml: 1 }} key={user?.uuid + "member-list"}>
      <Button
        key={user?.uuid}
        fullWidth
        variant="contained"
        color="primary"
        sx={{ borderRadius: "13px" }}
      >
        Add Contact
      </Button>
      {members.map((conversation: Conversation) => (
        <ChatButton
          key={conversation.id} 
          conversation={conversation}
        />
      ))}
      <Typography variant="h6" sx={{ ml: 1, mt: 2 }}>
        {user?.name}
      </Typography>
    </List>
  );
};

export default MemberList;
