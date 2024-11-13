import { RootState } from "@/store/store";
import { Box, Container, Typography } from "@mui/material";
import { useSelector } from "react-redux";

const Dashboard: React.FC = () => {
    const user = useSelector((state: RootState) => state.user.user);
  
    return (
      <Container maxWidth="sm">
        <Box
          display="flex"
          flexDirection="column"
          alignItems="center"
          justifyContent="center"
          minHeight="100vh"
        >
          <Typography variant="h4" component="h1" gutterBottom>
            Dashboard
          </Typography>
          {user && (
            <Box mt={4}>
              <Typography variant="h6" component="h2">
                Welcome, {user.name}!
              </Typography>
            </Box>
          )}
        </Box>
      </Container>
    );
  };
  
  export default Dashboard;