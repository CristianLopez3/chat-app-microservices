// src/store/index.ts
import { configureStore } from '@reduxjs/toolkit';
import userReducer from './users/user.slice'
import messagesReducer from './messages/message.slice'

const store = configureStore({
  reducer: {
    user: userReducer,
    messages: messagesReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;