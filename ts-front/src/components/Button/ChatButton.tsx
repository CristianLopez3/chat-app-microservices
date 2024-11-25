import { ListItem, ListItemAvatar, ListItemText, Typography } from '@mui/material';
import { StringAvatar } from '../avatar'; // Asegúrate de importar StringAvatar correctamente
import { ChatMember, UserResponse } from '@/models';

type ChatButtonProps = {
  member: UserResponse;
  selected: boolean;
  onChatSelect: (member: UserResponse) => void;
};

export const ChatButton: React.FC<ChatButtonProps> = ({member, selected, onChatSelect }) => {
  const { userId, name, username } = member;

  return (
    <ListItem
      key={userId + Math.random()}
      alignItems="flex-start"
      sx={{
        bgcolor: selected ? '#ddddee' : '#f9fafc',
        borderRadius: 3,
        cursor: 'pointer',
        '&:hover': {
          bgcolor: selected ? '#ddddee' : '#f0f0f5',
        },
        width: '100%',
      }}
      onClick={() => onChatSelect(member)}
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