import { RootState } from "@/store/store";
import { Box, Container, Typography } from "@mui/material";
import { useSelector } from "react-redux";
import App from "./App";
import { AppRoutes } from "@/models";
import { Link } from "react-router-dom";

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
              <Link to={AppRoutes.private.chat} >CHAT </Link>
            </Box>
          )}
        </Box>
      </Container>
    );
  };
  
  export default Dashboard;