import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';

const stringAvatar = (name: string) => {
  const nameParts = name.split(' ');
  const initials = nameParts.length > 1 
    ? `${nameParts[0][0]}${nameParts[1][0]}` 
    : `${nameParts[0][0]}`;

  return {
    sx: {
      bgcolor: '#000',
      borderRadius: '20%',
    },
    children: initials,
  };
}

type Properties = {
  name: string;
}

export const StringAvatar = ({ name }: Properties) => {
  return (
    <Stack direction="row" spacing={2}>
      <Avatar {...stringAvatar(name)} />
    </Stack>
  );
}