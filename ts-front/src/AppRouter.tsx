import { createBrowserRouter } from "react-router-dom";
import { AppRoutes } from "./models";
import Home from "@/public/Home";
import MessageArea from "@/private/chat/MessageArea";
import Dashboard from "./public/Dashboard";
import SignUp from "./public/SignUp";
import Login from "./public/Login";

export const router = createBrowserRouter([
  {
    path: AppRoutes.index,
    element: <Home />
  },
  {
    path: AppRoutes.private.chat,
    element: <MessageArea />
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