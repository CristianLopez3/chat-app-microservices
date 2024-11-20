import { createTheme } from "@mui/material";

const theme = createTheme({
  typography: {
    fontFamily: 'Poppins, Arial, sans-serif',
  },
  palette:{
    primary: {
      main: '#000000'
    },
    secondary: {
      main: '#f9fafc'
    }
  }
})

export default theme;