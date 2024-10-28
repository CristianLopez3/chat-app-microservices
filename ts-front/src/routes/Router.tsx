import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import MessageArea from "../chat/MessageArea";
import { ROUTES } from "./routes";
import Login from "../pages/Login";


export const router = createBrowserRouter([
  {
    path: ROUTES.INDEX,
    element: <Home />
  },
  {
    path: ROUTES.CHAT,
    element: <MessageArea />
  },
  {
    path: ROUTES.LOGIN,
    element: <Login />
  }
])