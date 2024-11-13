import React from "react";
import { Box, Button, TextField } from "@mui/material";

type RegisterProps = {
  userData: any;
  handleUsernameInput: (event: React.ChangeEvent<HTMLInputElement>) => void;
  registerUser: () => void;
};

const Register: React.FC<RegisterProps> = ({ userData, handleUsernameInput, registerUser }) => {
  return (
    <div className="register">
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        justifyContent="center"
        minWidth={{ sm: 300, md: 450, lg: 600 }}
      >
        <TextField
          margin="normal"
          required
          fullWidth
          id="user-name"
          label="Enter your name"
          name="username"
          autoComplete="username"
          autoFocus
          value={userData.username}
          onChange={handleUsernameInput}
        />
        <Button
          type="button"
          fullWidth
          variant="contained"
          color="primary"
          sx={{ mt: 3, mb: 2 }}
          onClick={registerUser}
        >
          connect
        </Button>
      </Box>
    </div>
  );
};

export default Register;