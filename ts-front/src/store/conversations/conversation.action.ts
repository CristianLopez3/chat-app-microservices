// src/store/userActions.ts
import { createAsyncThunk } from '@reduxjs/toolkit';
import { createConversation, getUserConversationsById } from '@/services';
import { Conversation, ConversationDTO } from '@/models';

export const createConversationAction = createAsyncThunk<Conversation, ConversationDTO>(
  'conversation/createConversation',
  async (conversation, { rejectWithValue }) => {
    try {
      const { call } = createConversation(conversation);
      const response = await call;
      return response.data;
    } catch (error: any) {
      if (!error.response) {
        throw error;
      }
      return rejectWithValue(error.response.data);
    }
  }
);


export const getUserConversationsAction = createAsyncThunk<Conversation[], string>(
  "conversation/getUserConversations",
  async (userId, { rejectWithValue }) => {
    try {
      const { call } = getUserConversationsById(userId);
      const response = await call;
      return response.data;
    } catch (error: any) {
      if (!error.response) {
        throw error;
      }
      return rejectWithValue(error.response.data);
    }
  }
)

export const selectChatAction = createAsyncThunk<Conversation, Conversation>(
  "conversation/selectChat",
  (conversation) => {
    console.log(conversation);
    return conversation;
  }
)