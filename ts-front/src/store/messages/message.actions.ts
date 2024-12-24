import { createAsyncThunk } from '@reduxjs/toolkit';
import { ChatPayload } from '@/models';

export const addMessage = createAsyncThunk<ChatPayload, ChatPayload>(
  'messages/addMessage',
  async (message: ChatPayload) => {
    return message;
  }
);