import { ListItem, ListItemAvatar, ListItemText, Typography } from '@mui/material';
import { StringAvatar } from '../avatar';
import { Conversation } from '@/models';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '@/store';
import { selectChatAction } from '@/store/conversations/conversation.action';

type ChatButtonProps = {
  conversation: Conversation;
  // selected: boolean;
  // onConversationSelect: (conversation: Conversation) => void;
};

export const ChatButton: React.FC<ChatButtonProps> = ({ conversation }) => {
  const dispatch = useDispatch<AppDispatch>();
  const { selectedConversation } = useSelector((state: RootState) => state.conversations);
  const { user } = useSelector((state: RootState) => state.user);
  const { isGroup, groupMetadata, participants, id } = conversation;
  const name = isGroup ? groupMetadata!.name :
    participants.filter(participant => participant.uuid !== user?.uuid)[0]?.name || 'Unknown User';
  const username = isGroup ? 'Group' : participants.filter(participant => participant.uuid !== user?.uuid)[0]?.username || 'Unknown User';
  const isSelected = selectedConversation ? selectedConversation.id === id : false;

  const handleChatSelect = () => {
    dispatch(selectChatAction(conversation));
  }

  return (
    <ListItem
      key={id}
      alignItems="flex-start"
      sx={{
        bgcolor: isSelected ? '#ddddee' : '#f9fafc',
        borderRadius: 3,
        cursor: 'pointer',
        '&:hover': {
          bgcolor: isSelected ? '#ddddee' : '#f0f0f5',
        },
        width: '100%',
      }}
    onClick={handleChatSelect}
    >
      <ListItemAvatar>
        <StringAvatar name={name} />
      </ListItemAvatar>
      <ListItemText
        color='#7678ed'
        primary={name}
        secondary={
          <>
            <Typography
              component="span"
              variant="body2"
              color='#969495'
              sx={{ color: 'text', display: 'inline' }}
            >
              {username}
            </Typography>
          </>
        }
      />
    </ListItem>
  );
};