import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Typography from '@mui/material/Typography';
import { StringAvatar } from '../avatar';


type ChatButtonProps = {
  id: string;
  name: string;
  lastMessage: string;
  selected: boolean;
  onClick: (id: string) => void;
};


export const ChatButton: React.FC<ChatButtonProps> = ({ id, name, lastMessage, selected, onClick }) => {
  return (
    <ListItem
      key={id}
      alignItems="flex-start"
      sx={{ bgcolor: selected ? '#eeeef8' : '#f9fafc', borderRadius: 3, cursor: 'pointer' }}
      onClick={() => onClick(id)}
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
              {lastMessage}
            </Typography>
          </>
        }
      />
    </ListItem>
  );
};