import { Box,  Typography } from '@mui/material';
import NavBar from '@/components/NavBar/NavBar';

const Home = () => {

  return (
    <>
      <NavBar />
      <Box component="main" sx={{ p: 3 }}>
      <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            minHeight: '50vh',
            textAlign: 'center',
            p: 4,
            borderRadius: 2,
          }}
        >
          <Typography variant="h2" component="h1" sx={{fontWeight: 700}} gutterBottom>
            Chat Application
          </Typography>
          <Typography variant="h6" component="p" gutterBottom>
            Connect with your friends and family instantly with our real-time chat application.
          </Typography>
        </Box>
      </Box>
    </>
  )
}

export default Home
