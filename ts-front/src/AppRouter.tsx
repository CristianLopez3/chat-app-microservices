import { createBrowserRouter } from "react-router-dom";
import { AppRoutes } from "./models";
import Home from "./public/pages/Home";
import Login from "./public/pages/Login";
import MessageArea from "./public/chat/MessageArea";
import Dashboard from "./public/pages/Dashboard";



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
  }
])