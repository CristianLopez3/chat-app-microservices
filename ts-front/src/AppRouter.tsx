import { createBrowserRouter } from "react-router-dom";
import { AppRoutes } from "./models";
import Home from "@/public/Home";
import SignUp from "./public/SignUp";
import Login from "./public/Login";
import { Chat } from "@mui/icons-material";
import Dashboard from "./private/dashboard/Dashboard";

export const router = createBrowserRouter([
  {
    path: AppRoutes.index,
    element: <Home />
  },
  {
    path: AppRoutes.private.chat,
    element: <Chat />
  },
  {
    path: AppRoutes.private.dashboard,
    element: <Dashboard />
  },
  {
    path: AppRoutes.public.login,
    element: <Login />
  },
  {
    path: AppRoutes.public.signUp,
    element: <SignUp />
  }
])